// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import java.util.*;
import java.util.function.Function;

public class S3DatasetComponentBlob implements ProtoType {
  public PathDatasetComponentBlob Path;

  public S3DatasetComponentBlob() {
    this.Path = null;
  }

  public Boolean isEmpty() {
    if (this.Path != null) {
      return false;
    }
    return true;
  }

  // TODO: not consider order on lists
  public Boolean equals(S3DatasetComponentBlob other) {
    if (other == null) {
      return false;
    }
    {
      Function3<PathDatasetComponentBlob, PathDatasetComponentBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.Path != null || other.Path != null) {
        if (this.Path == null && other.Path != null) return false;
        if (this.Path != null && other.Path == null) return false;
        if (!f.apply(this.Path, other.Path)) return false;
      }
    }
    return true;
  }

  public S3DatasetComponentBlob setPath(PathDatasetComponentBlob value) {
    this.Path = value;
    return this;
  }

  public static S3DatasetComponentBlob fromProto(
      ai.verta.modeldb.versioning.S3DatasetComponentBlob blob) {
    if (blob == null) {
      return null;
    }

    S3DatasetComponentBlob obj = new S3DatasetComponentBlob();
    {
      Function<ai.verta.modeldb.versioning.S3DatasetComponentBlob, PathDatasetComponentBlob> f =
          x -> PathDatasetComponentBlob.fromProto(blob.getPath());
      obj.Path = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.S3DatasetComponentBlob.Builder toProto() {
    ai.verta.modeldb.versioning.S3DatasetComponentBlob.Builder builder =
        ai.verta.modeldb.versioning.S3DatasetComponentBlob.newBuilder();
    {
      if (this.Path != null) {
        Function<ai.verta.modeldb.versioning.S3DatasetComponentBlob.Builder, Void> f =
            x -> {
              builder.setPath(this.Path.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitS3DatasetComponentBlob(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepPathDatasetComponentBlob(this.Path);
  }

  public S3DatasetComponentBlob postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitS3DatasetComponentBlob(this);
  }

  public S3DatasetComponentBlob postVisitDeep(Visitor visitor) throws ModelDBException {
    this.Path = visitor.postVisitDeepPathDatasetComponentBlob(this.Path);
    return this.postVisitShallow(visitor);
  }
}
