// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import java.util.*;
import java.util.function.Function;

public class Blob implements ProtoType {
  public DatasetBlob Dataset;
  public EnvironmentBlob Environment;
  public CodeBlob Code;
  public ConfigBlob Config;

  public Blob() {
    this.Dataset = null;
    this.Environment = null;
    this.Code = null;
    this.Config = null;
  }

  public Boolean isEmpty() {
    if (this.Dataset != null) {
      return false;
    }
    if (this.Environment != null) {
      return false;
    }
    if (this.Code != null) {
      return false;
    }
    if (this.Config != null) {
      return false;
    }
    return true;
  }

  // TODO: not consider order on lists
  public Boolean equals(Blob other) {
    if (other == null) {
      return false;
    }
    {
      Function3<DatasetBlob, DatasetBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Dataset != null || other.Dataset != null) {
        if (this.Dataset == null && other.Dataset != null) return false;
        if (this.Dataset != null && other.Dataset == null) return false;
        if (!f.apply(this.Dataset, other.Dataset)) return false;
      }
    }
    {
      Function3<EnvironmentBlob, EnvironmentBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Environment != null || other.Environment != null) {
        if (this.Environment == null && other.Environment != null) return false;
        if (this.Environment != null && other.Environment == null) return false;
        if (!f.apply(this.Environment, other.Environment)) return false;
      }
    }
    {
      Function3<CodeBlob, CodeBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Code != null || other.Code != null) {
        if (this.Code == null && other.Code != null) return false;
        if (this.Code != null && other.Code == null) return false;
        if (!f.apply(this.Code, other.Code)) return false;
      }
    }
    {
      Function3<ConfigBlob, ConfigBlob, Boolean> f = (x, y) -> x.equals(y);
      if (this.Config != null || other.Config != null) {
        if (this.Config == null && other.Config != null) return false;
        if (this.Config != null && other.Config == null) return false;
        if (!f.apply(this.Config, other.Config)) return false;
      }
    }
    return true;
  }

  public Blob setDataset(DatasetBlob value) {
    this.Dataset = value;
    return this;
  }

  public Blob setEnvironment(EnvironmentBlob value) {
    this.Environment = value;
    return this;
  }

  public Blob setCode(CodeBlob value) {
    this.Code = value;
    return this;
  }

  public Blob setConfig(ConfigBlob value) {
    this.Config = value;
    return this;
  }

  public static Blob fromProto(ai.verta.modeldb.versioning.Blob blob) {
    if (blob == null) {
      return null;
    }

    Blob obj = new Blob();
    {
      Function<ai.verta.modeldb.versioning.Blob, DatasetBlob> f =
          x -> DatasetBlob.fromProto(blob.getDataset());
      obj.Dataset = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, EnvironmentBlob> f =
          x -> EnvironmentBlob.fromProto(blob.getEnvironment());
      obj.Environment = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, CodeBlob> f =
          x -> CodeBlob.fromProto(blob.getCode());
      obj.Code = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.Blob, ConfigBlob> f =
          x -> ConfigBlob.fromProto(blob.getConfig());
      obj.Config = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.Blob.Builder toProto() {
    ai.verta.modeldb.versioning.Blob.Builder builder =
        ai.verta.modeldb.versioning.Blob.newBuilder();
    {
      if (this.Dataset != null) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setDataset(this.Dataset.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Environment != null) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setEnvironment(this.Environment.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Code != null) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setCode(this.Code.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Config != null) {
        Function<ai.verta.modeldb.versioning.Blob.Builder, Void> f =
            x -> {
              builder.setConfig(this.Config.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitBlob(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepDatasetBlob(this.Dataset);
    visitor.preVisitDeepEnvironmentBlob(this.Environment);
    visitor.preVisitDeepCodeBlob(this.Code);
    visitor.preVisitDeepConfigBlob(this.Config);
  }

  public Blob postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitBlob(this);
  }

  public Blob postVisitDeep(Visitor visitor) throws ModelDBException {
    this.Dataset = visitor.postVisitDeepDatasetBlob(this.Dataset);
    this.Environment = visitor.postVisitDeepEnvironmentBlob(this.Environment);
    this.Code = visitor.postVisitDeepCodeBlob(this.Code);
    this.Config = visitor.postVisitDeepConfigBlob(this.Config);
    return this.postVisitShallow(visitor);
  }
}
