package ai.verta.modeldb.lineage;

import ai.verta.common.ModelDBResourceEnum.ModelDBServiceResourceTypes;
import ai.verta.modeldb.AddLineage;
import ai.verta.modeldb.DeleteLineage;
import ai.verta.modeldb.FindAllInputs;
import ai.verta.modeldb.FindAllInputsOutputs;
import ai.verta.modeldb.FindAllInputsOutputs.Response;
import ai.verta.modeldb.FindAllInputsOutputs.Response.Builder;
import ai.verta.modeldb.FindAllOutputs;
import ai.verta.modeldb.LineageEntry;
import ai.verta.modeldb.LineageEntry.DescriptionCase;
import ai.verta.modeldb.LineageEntryBatchResponse;
import ai.verta.modeldb.LineageEntryBatchResponseSingle;
import ai.verta.modeldb.LineageServiceGrpc.LineageServiceImplBase;
import ai.verta.modeldb.ModelDBAuthInterceptor;
import ai.verta.modeldb.ModelDBConstants;
import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.VersioningLineageEntry;
import ai.verta.modeldb.authservice.RoleService;
import ai.verta.modeldb.entities.ExperimentRunEntity;
import ai.verta.modeldb.entities.versioning.RepositoryEntity;
import ai.verta.modeldb.experimentRun.ExperimentRunDAO;
import ai.verta.modeldb.monitoring.QPSCountResource;
import ai.verta.modeldb.monitoring.RequestLatencyResource;
import ai.verta.modeldb.utils.ModelDBUtils;
import ai.verta.modeldb.versioning.BlobDAO;
import ai.verta.modeldb.versioning.CommitDAO;
import ai.verta.modeldb.versioning.RepositoryDAO;
import ai.verta.modeldb.versioning.RepositoryIdentification;
import ai.verta.uac.ModelDBActionEnum.ModelDBServiceActions;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.rpc.Status;
import io.grpc.Status.Code;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

public class LineageServiceImpl extends LineageServiceImplBase {

  private static final Logger LOGGER = LogManager.getLogger(LineageServiceImpl.class);
  private final ExperimentRunDAO experimentRunDAO;
  private final RepositoryDAO repositoryDAO;
  private final CommitDAO commitDAO;
  private final LineageDAO lineageDAO;
  private final BlobDAO blobDAO;
  private final RoleService roleService;

  public LineageServiceImpl(
      LineageDAO lineageDAO,
      ExperimentRunDAO experimentRunDAO,
      RepositoryDAO repositoryDAO,
      CommitDAO commitDAO,
      BlobDAO blobDAO,
      RoleService roleService) {
    this.lineageDAO = lineageDAO;
    this.experimentRunDAO = experimentRunDAO;
    this.repositoryDAO = repositoryDAO;
    this.commitDAO = commitDAO;
    this.blobDAO = blobDAO;
    this.roleService = roleService;
  }

  @Override
  public void addLineage(AddLineage request, StreamObserver<AddLineage.Response> responseObserver) {
    QPSCountResource.inc();
    try {
      if (request.getInputCount() == 0 && request.getOutputCount() == 0) {
        throw new ModelDBException("Input and output not specified", Code.INVALID_ARGUMENT);
      }
      try (RequestLatencyResource latencyResource =
          new RequestLatencyResource(ModelDBAuthInterceptor.METHOD_NAME.get())) {
        AddLineage.Response response =
            lineageDAO.addLineage(request, this::checkResourcesExistsAndAccessible);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }
    } catch (Exception e) {
      ModelDBUtils.observeError(responseObserver, e, AddLineage.Response.getDefaultInstance());
    }
  }

  @Override
  public void deleteLineage(
      DeleteLineage request, StreamObserver<DeleteLineage.Response> responseObserver) {
    QPSCountResource.inc();
    try {
      try (RequestLatencyResource latencyResource =
          new RequestLatencyResource(ModelDBAuthInterceptor.METHOD_NAME.get())) {
        DeleteLineage.Response response =
            lineageDAO.deleteLineage(request, this::checkResourcesExistsAndAccessible);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }
    } catch (Exception e) {
      ModelDBUtils.observeError(responseObserver, e, DeleteLineage.Response.getDefaultInstance());
    }
  }

  @Override
  public void findAllInputs(
      FindAllInputs request, StreamObserver<FindAllInputs.Response> responseObserver) {
    QPSCountResource.inc();
    try {
      if (request.getItemsCount() == 0) {
        throw new ModelDBException("Items not specified", Code.INVALID_ARGUMENT);
      }
      try (RequestLatencyResource latencyResource =
          new RequestLatencyResource(ModelDBAuthInterceptor.METHOD_NAME.get())) {
        FindAllInputs.Response response =
            lineageDAO.findAllInputs(
                request, this::checkResourcesExistsAndAccessible, this::filterInput);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }
    } catch (Exception e) {
      ModelDBUtils.observeError(responseObserver, e, FindAllInputs.Response.getDefaultInstance());
    }
  }

  @Override
  public void findAllOutputs(
      FindAllOutputs request, StreamObserver<FindAllOutputs.Response> responseObserver) {
    QPSCountResource.inc();
    try {
      if (request.getItemsCount() == 0) {
        throw new ModelDBException("Items not specified", Code.INVALID_ARGUMENT);
      }
      try (RequestLatencyResource latencyResource =
          new RequestLatencyResource(ModelDBAuthInterceptor.METHOD_NAME.get())) {
        FindAllOutputs.Response response =
            lineageDAO.findAllOutputs(
                request, this::checkResourcesExistsAndAccessible, this::filterOutput);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }
    } catch (Exception e) {
      ModelDBUtils.observeError(responseObserver, e, FindAllOutputs.Response.getDefaultInstance());
    }
  }

  @Override
  public void findAllInputsOutputs(
      FindAllInputsOutputs request,
      StreamObserver<FindAllInputsOutputs.Response> responseObserver) {
    QPSCountResource.inc();
    try {
      if (request.getItemsCount() == 0) {
        throw new ModelDBException("Items not specified", Code.INVALID_ARGUMENT);
      }
      try (RequestLatencyResource latencyResource =
          new RequestLatencyResource(ModelDBAuthInterceptor.METHOD_NAME.get())) {
        FindAllInputsOutputs.Response response =
            lineageDAO.findAllInputsOutputs(
                request, this::checkResourcesExistsAndAccessible, this::filterInputOutput);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }
    } catch (Exception e) {
      ModelDBUtils.observeError(
          responseObserver, e, FindAllInputsOutputs.Response.getDefaultInstance());
    }
  }

  private static class CommitSet {
    Set<String> commits = new HashSet<>();

    public boolean containsKey(String commitSha) {
      return commits.contains(commitSha);
    }

    public void add(String commitSha) {
      commits.add(commitSha);
    }
  }

  private static class RepositoryContainer {
    Map.Entry<RepositoryEntity, CommitSet> repository;

    RepositoryContainer(RepositoryEntity repo) {
      repository = new AbstractMap.SimpleEntry<>(repo, new CommitSet());
    }

    public CommitSet getValue() {
      return repository.getValue();
    }

    public RepositoryEntity getKey() {
      return repository.getKey();
    }
  }

  private FindAllInputs.Response filterInput(Session session, FindAllInputs.Response response) {
    FindAllInputs.Response.Builder builder = FindAllInputs.Response.newBuilder();
    builder.addAllInputs(filter(session, response.getInputsList()));
    return builder.build();
  }

  private FindAllOutputs.Response filterOutput(Session session, FindAllOutputs.Response response) {
    FindAllOutputs.Response.Builder builder = FindAllOutputs.Response.newBuilder();
    builder.addAllOutputs(filter(session, response.getOutputsList()));
    return builder.build();
  }

  private Response filterInputOutput(Session session, Response response) {
    Builder builder = Response.newBuilder();
    builder.addAllInputs(filter(session, response.getInputsList()));
    builder.addAllOutputs(filter(session, response.getOutputsList()));
    return builder.build();
  }

  private Iterable<LineageEntryBatchResponse> filter(
      Session session, List<LineageEntryBatchResponse> lineageEntryBatchResponses) {
    final Set<String> unfilteredExperimentRunIds = new HashSet<>();
    for (LineageEntryBatchResponse lineageEntryBatchResponse : lineageEntryBatchResponses) {
      List<LineageEntryBatchResponseSingle> lineageEntryBatchResponseItemsList =
          lineageEntryBatchResponse.getItemsList();
      for (LineageEntryBatchResponseSingle lineageEntryBatchResponseSingle :
          lineageEntryBatchResponseItemsList) {
        List<LineageEntry> itemList = lineageEntryBatchResponseSingle.getItemsList();
        for (LineageEntry lineageEntry : itemList) {
          if (lineageEntry.getDescriptionCase() == DescriptionCase.EXPERIMENT_RUN) {
            unfilteredExperimentRunIds.add(lineageEntry.getExperimentRun());
          }
        }
      }
    }
    final Set<String> filteredExperimentRunIds;
    if (unfilteredExperimentRunIds.isEmpty()) {
      filteredExperimentRunIds = unfilteredExperimentRunIds;
    } else {
      filteredExperimentRunIds = filter(session, unfilteredExperimentRunIds);
    }
    final Map<Long, RepositoryContainer> repositories = new HashMap<>();
    return lineageEntryBatchResponses.stream()
        .map(
            lineageEntryBatchResponse ->
                filterLineageEntryBatchResponse(
                    session,
                    unfilteredExperimentRunIds,
                    repositories,
                    lineageEntryBatchResponse,
                    filteredExperimentRunIds))
        .collect(Collectors.toList());
  }

  private LineageEntryBatchResponse filterLineageEntryBatchResponse(
      Session session,
      Set<String> unfilteredExperimentRunIds,
      Map<Long, RepositoryContainer> repositories,
      LineageEntryBatchResponse lineageEntryBatchResponse,
      Set<String> filteredExperimentRunIds) {
    List<LineageEntryBatchResponseSingle> lineageEntryBatchResponseItemsList =
        lineageEntryBatchResponse.getItemsList();
    List<LineageEntryBatchResponseSingle> result =
        lineageEntryBatchResponseItemsList.stream()
            .flatMap(
                lineageEntryBatchResponseSingle ->
                    filterLineageEntryBatchResponseSingle(
                        session,
                        unfilteredExperimentRunIds,
                        repositories,
                        lineageEntryBatchResponseSingle,
                        filteredExperimentRunIds))
            .collect(Collectors.toList());
    return LineageEntryBatchResponse.newBuilder().addAllItems(result).build();
  }

  private Stream<? extends LineageEntryBatchResponseSingle> filterLineageEntryBatchResponseSingle(
      Session session,
      Set<String> unfilteredExperimentRunIds,
      Map<Long, RepositoryContainer> repositories,
      LineageEntryBatchResponseSingle lineageEntryBatchResponseSingle,
      Set<String> filteredExperimentRunIds) {
    List<LineageEntryBatchResponseSingle> newLineageEntryBatchResponseSingleList =
        new LinkedList<>();
    List<LineageEntry> itemList = lineageEntryBatchResponseSingle.getItemsList();
    List<LineageEntry> filterResult =
        itemList.stream()
            .filter(
                lineageEntry ->
                    filterLineageEntry(
                        session,
                        unfilteredExperimentRunIds,
                        repositories,
                        lineageEntry,
                        filteredExperimentRunIds))
            .collect(Collectors.toList());
    if (filterResult.size() != 0) {
      newLineageEntryBatchResponseSingleList.add(
          LineageEntryBatchResponseSingle.newBuilder()
              .setId(lineageEntryBatchResponseSingle.getId())
              .addAllItems(filterResult)
              .build());
    }
    return newLineageEntryBatchResponseSingleList.stream();
  }

  private boolean filterLineageEntry(
      Session session,
      Set<String> unfilteredExperimentRunIds,
      Map<Long, RepositoryContainer> repositories,
      LineageEntry lineageEntry,
      Set<String> filteredExperimentRunIds) {
    try {
      validate(
          session,
          unfilteredExperimentRunIds,
          repositories,
          lineageEntry,
          filteredExperimentRunIds);
      return true;
    } catch (StatusRuntimeException | ModelDBException e) {
      LOGGER.warn("Can't access entity {}", e.getMessage());
      return false;
    } catch (NoSuchAlgorithmException | InvalidProtocolBufferException e) {
      LOGGER.error("Unexpected error {}", e.getMessage());
      Status status =
          Status.newBuilder()
              .setCode(com.google.rpc.Code.INTERNAL_VALUE)
              .setMessage(e.getMessage())
              .addDetails(Any.pack(LineageEntryBatchResponse.getDefaultInstance()))
              .build();
      throw StatusProto.toStatusRuntimeException(status);
    }
  }

  private void checkResourcesExistsAndAccessible(Session session, List<LineageEntry> lineageEntries)
      throws ModelDBException, NoSuchAlgorithmException, InvalidProtocolBufferException {
    Set<String> experimentRunIds = new HashSet<>();
    Map<Long, RepositoryContainer> blobs = new HashMap<>();
    for (LineageEntry lineageEntry : lineageEntries) {
      validate(session, experimentRunIds, blobs, lineageEntry);
    }
    if (!experimentRunIds.isEmpty()) {
      validate(session, experimentRunIds);
    }
  }

  private Set<String> filter(Session session, Set<String> experimentRunIds) {
    Set<String> result = new HashSet<>();
    LinkedList<String> experimentRunIdsList = new LinkedList<>(experimentRunIds);
    List<ExperimentRunEntity> experimentRunEntities =
        experimentRunDAO.getExperimentRunEntitiesBatch(session, experimentRunIdsList);
    for (ExperimentRunEntity experimentRunEntity : experimentRunEntities) {
      if (experimentRunEntity == null) {
        LOGGER.warn(ModelDBConstants.EXPERIMENT_RUN_NOT_FOUND);
      } else {
        try {
          roleService.validateEntityUserWithUserInfo(
              ModelDBServiceResourceTypes.PROJECT,
              experimentRunEntity.getProject_id(),
              ModelDBServiceActions.READ);
          result.add(experimentRunEntity.getId());
        } catch (StatusRuntimeException e) {
          LOGGER.warn("Can't access entity {}", e.getMessage());
        } catch (InvalidProtocolBufferException e) {
          LOGGER.error("Unexpected error {}", e.getMessage());
          Status status =
              Status.newBuilder()
                  .setCode(com.google.rpc.Code.INTERNAL_VALUE)
                  .setMessage(e.getMessage())
                  .addDetails(Any.pack(LineageEntryBatchResponse.getDefaultInstance()))
                  .build();
          throw StatusProto.toStatusRuntimeException(status);
        }
      }
    }
    return result;
  }

  private void validate(Session session, Set<String> experimentRunIds)
      throws ModelDBException, InvalidProtocolBufferException {
    LinkedList<String> experimentRunIdsList = new LinkedList<>(experimentRunIds);
    List<ExperimentRunEntity> experimentRunEntities =
        experimentRunDAO.getExperimentRunEntitiesBatch(session, experimentRunIdsList);
    if (experimentRunEntities.size() != experimentRunIdsList.size()) {
      throw new ModelDBException("Can't find all experiment runs");
    }
    for (ExperimentRunEntity experimentRunEntity : experimentRunEntities) {
      if (experimentRunEntity == null) {
        LOGGER.warn(ModelDBConstants.EXPERIMENT_RUN_NOT_FOUND);
        throw new ModelDBException(ModelDBConstants.EXPERIMENT_RUN_NOT_FOUND, Code.NOT_FOUND);
      }
      roleService.validateEntityUserWithUserInfo(
          ModelDBServiceResourceTypes.PROJECT,
          experimentRunEntity.getProject_id(),
          ModelDBServiceActions.READ);
    }
  }

  private void validate(
      Session session,
      Set<String> experimentRunIds,
      Map<Long, RepositoryContainer> repositories,
      LineageEntry lineageEntry)
      throws InvalidProtocolBufferException, ModelDBException, NoSuchAlgorithmException {
    validate(session, experimentRunIds, repositories, lineageEntry, null);
  }

  private boolean validate(
      Session session,
      Set<String> experimentRunIds,
      Map<Long, RepositoryContainer> repositories,
      LineageEntry lineageEntry,
      Set<String> filteredExperimentRunIds)
      throws InvalidProtocolBufferException, ModelDBException, NoSuchAlgorithmException {
    switch (lineageEntry.getDescriptionCase()) {
      case EXPERIMENT_RUN:
        experimentRunIds.add(lineageEntry.getExperimentRun());
        if (filteredExperimentRunIds == null) {
          return true;
        }
        return filteredExperimentRunIds.contains(lineageEntry.getExperimentRun());
      case BLOB:
        VersioningLineageEntry blob = lineageEntry.getBlob();
        long repositoryId = blob.getRepositoryId();
        RepositoryEntity repo;
        CommitSet result;
        if (!repositories.containsKey(repositoryId)) {
          // checks permissions and gets a repository
          repo =
              repositoryDAO.getRepositoryById(
                  session, RepositoryIdentification.newBuilder().setRepoId(repositoryId).build());
          repositories.put(repositoryId, new RepositoryContainer(repo));
          result = repositories.get(repositoryId).getValue();
        } else {
          RepositoryContainer entityMapEntry = repositories.get(repositoryId);
          repo = entityMapEntry.getKey();
          result = entityMapEntry.getValue();
        }
        String commitSha = blob.getCommitSha();
        if (!result.containsKey(commitSha)) {
          commitDAO.getCommitEntity(session, commitSha, session2 -> repo);
          result.add(commitSha);
        }
        blobDAO.getCommitComponent(session2 -> repo, commitSha, blob.getLocationList());
        break;
    }
    return true;
  }
}
