// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class S3DatasetDiff implements ProtoType {
  public List<S3DatasetComponentDiff> Components;

  public S3DatasetDiff() {
    this.Components = null;
  }

  public Boolean isEmpty() {
    if (this.Components != null) {
      return false;
    }
    return true;
  }

  // TODO: not consider order on lists
  public Boolean equals(S3DatasetDiff other) {
    if (other == null) {
      return false;
    }
    {
      Function3<List<S3DatasetComponentDiff>, List<S3DatasetComponentDiff>, Boolean> f =
          (x2, y2) ->
              IntStream.range(0, Math.min(x2.size(), y2.size()))
                  .mapToObj(
                      i -> {
                        Function3<S3DatasetComponentDiff, S3DatasetComponentDiff, Boolean> f2 =
                            (x, y) -> x.equals(y);
                        return f2.apply(x2.get(i), y2.get(i));
                      })
                  .filter(x -> x != null)
                  .collect(Collectors.toList())
                  .isEmpty();
      if (this.Components != null || other.Components != null) {
        if (this.Components == null && other.Components != null) return false;
        if (this.Components != null && other.Components == null) return false;
        if (!f.apply(this.Components, other.Components)) return false;
      }
    }
    return true;
  }

  public S3DatasetDiff setComponents(List<S3DatasetComponentDiff> value) {
    this.Components = value;
    return this;
  }

  public static S3DatasetDiff fromProto(ai.verta.modeldb.versioning.S3DatasetDiff blob) {
    if (blob == null) {
      return null;
    }

    S3DatasetDiff obj = new S3DatasetDiff();
    {
      Function<ai.verta.modeldb.versioning.S3DatasetDiff, List<S3DatasetComponentDiff>> f =
          x ->
              blob.getComponentsList().stream()
                  .map(S3DatasetComponentDiff::fromProto)
                  .collect(Collectors.toList());
      obj.Components = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.S3DatasetDiff.Builder toProto() {
    ai.verta.modeldb.versioning.S3DatasetDiff.Builder builder =
        ai.verta.modeldb.versioning.S3DatasetDiff.newBuilder();
    {
      if (this.Components != null) {
        Function<ai.verta.modeldb.versioning.S3DatasetDiff.Builder, Void> f =
            x -> {
              builder.addAllComponents(
                  this.Components.stream()
                      .map(y -> y.toProto().build())
                      .collect(Collectors.toList()));
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitS3DatasetDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepListOfS3DatasetComponentDiff(this.Components);
  }

  public S3DatasetDiff postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitS3DatasetDiff(this);
  }

  public S3DatasetDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.Components = visitor.postVisitDeepListOfS3DatasetComponentDiff(this.Components);
    return this.postVisitShallow(visitor);
  }
}
