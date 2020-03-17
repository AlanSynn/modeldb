// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import java.util.*;
import java.util.function.Function;

public class HyperparameterConfigDiff implements ProtoType {
  public DiffStatusEnumDiffStatus Status;
  public String Name;
  public HyperparameterValuesConfigBlob A;
  public HyperparameterValuesConfigBlob B;

  public HyperparameterConfigDiff() {
    this.Status = null;
    this.Name = null;
    this.A = null;
    this.B = null;
  }

  public Boolean isEmpty() {
    if (this.Status != null) {
      return false;
    }
    if (this.Name != null) {
      return false;
    }
    if (this.A != null) {
      return false;
    }
    if (this.B != null) {
      return false;
    }
    return true;
  }

  // TODO: not consider order on lists
  public Boolean equals(HyperparameterConfigDiff other) {
    if (other == null) {
      return false;
    }
    {
      Function3<DiffStatusEnumDiffStatus, DiffStatusEnumDiffStatus, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.Status != null || other.Status != null) {
        if (this.Status == null && other.Status != null) return false;
        if (this.Status != null && other.Status == null) return false;
        if (!f.apply(this.Status, other.Status)) return false;
      }
    }
    {
      Function3<String, String, Boolean> f = (x, y) -> x.equals(y);
      if (this.Name != null || other.Name != null) {
        if (this.Name == null && other.Name != null) return false;
        if (this.Name != null && other.Name == null) return false;
        if (!f.apply(this.Name, other.Name)) return false;
      }
    }
    {
      Function3<HyperparameterValuesConfigBlob, HyperparameterValuesConfigBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.A != null || other.A != null) {
        if (this.A == null && other.A != null) return false;
        if (this.A != null && other.A == null) return false;
        if (!f.apply(this.A, other.A)) return false;
      }
    }
    {
      Function3<HyperparameterValuesConfigBlob, HyperparameterValuesConfigBlob, Boolean> f =
          (x, y) -> x.equals(y);
      if (this.B != null || other.B != null) {
        if (this.B == null && other.B != null) return false;
        if (this.B != null && other.B == null) return false;
        if (!f.apply(this.B, other.B)) return false;
      }
    }
    return true;
  }

  public HyperparameterConfigDiff setStatus(DiffStatusEnumDiffStatus value) {
    this.Status = value;
    return this;
  }

  public HyperparameterConfigDiff setName(String value) {
    this.Name = value;
    return this;
  }

  public HyperparameterConfigDiff setA(HyperparameterValuesConfigBlob value) {
    this.A = value;
    return this;
  }

  public HyperparameterConfigDiff setB(HyperparameterValuesConfigBlob value) {
    this.B = value;
    return this;
  }

  public static HyperparameterConfigDiff fromProto(
      ai.verta.modeldb.versioning.HyperparameterConfigDiff blob) {
    if (blob == null) {
      return null;
    }

    HyperparameterConfigDiff obj = new HyperparameterConfigDiff();
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, DiffStatusEnumDiffStatus> f =
          x -> DiffStatusEnumDiffStatus.fromProto(blob.getStatus());
      obj.Status = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, String> f =
          x -> (blob.getName());
      obj.Name = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, HyperparameterValuesConfigBlob>
          f = x -> HyperparameterValuesConfigBlob.fromProto(blob.getA());
      obj.A = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff, HyperparameterValuesConfigBlob>
          f = x -> HyperparameterValuesConfigBlob.fromProto(blob.getB());
      obj.B = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder toProto() {
    ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder builder =
        ai.verta.modeldb.versioning.HyperparameterConfigDiff.newBuilder();
    {
      if (this.Status != null) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setStatus(this.Status.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Name != null) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setName(this.Name);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.A != null) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setA(this.A.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.B != null) {
        Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder, Void> f =
            x -> {
              builder.setB(this.B.toProto());
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitHyperparameterConfigDiff(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepDiffStatusEnumDiffStatus(this.Status);
    visitor.preVisitDeepString(this.Name);
    visitor.preVisitDeepHyperparameterValuesConfigBlob(this.A);
    visitor.preVisitDeepHyperparameterValuesConfigBlob(this.B);
  }

  public HyperparameterConfigDiff postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitHyperparameterConfigDiff(this);
  }

  public HyperparameterConfigDiff postVisitDeep(Visitor visitor) throws ModelDBException {
    this.Status = visitor.postVisitDeepDiffStatusEnumDiffStatus(this.Status);
    this.Name = visitor.postVisitDeepString(this.Name);
    this.A = visitor.postVisitDeepHyperparameterValuesConfigBlob(this.A);
    this.B = visitor.postVisitDeepHyperparameterValuesConfigBlob(this.B);
    return this.postVisitShallow(visitor);
  }
}
