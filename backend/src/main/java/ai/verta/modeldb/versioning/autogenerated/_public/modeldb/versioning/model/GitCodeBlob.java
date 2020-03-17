// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.diff.*;
import ai.verta.modeldb.versioning.blob.diff.Function3;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;
import java.util.*;
import java.util.function.Function;

public class GitCodeBlob implements ProtoType {
  public String Repo;
  public String Hash;
  public String Branch;
  public String Tag;
  public Boolean IsDirty;

  public GitCodeBlob() {
    this.Repo = null;
    this.Hash = null;
    this.Branch = null;
    this.Tag = null;
    this.IsDirty = false;
  }

  public Boolean isEmpty() {
    if (this.Repo != null) {
      return false;
    }
    if (this.Hash != null) {
      return false;
    }
    if (this.Branch != null) {
      return false;
    }
    if (this.Tag != null) {
      return false;
    }
    if (this.IsDirty != null) {
      return false;
    }
    return true;
  }

  // TODO: not consider order on lists
  public Boolean equals(GitCodeBlob other) {
    if (other == null) {
      return false;
    }
    {
      Function3<String, String, Boolean> f = (x, y) -> x.equals(y);
      if (this.Repo != null || other.Repo != null) {
        if (this.Repo == null && other.Repo != null) return false;
        if (this.Repo != null && other.Repo == null) return false;
        if (!f.apply(this.Repo, other.Repo)) return false;
      }
    }
    {
      Function3<String, String, Boolean> f = (x, y) -> x.equals(y);
      if (this.Hash != null || other.Hash != null) {
        if (this.Hash == null && other.Hash != null) return false;
        if (this.Hash != null && other.Hash == null) return false;
        if (!f.apply(this.Hash, other.Hash)) return false;
      }
    }
    {
      Function3<String, String, Boolean> f = (x, y) -> x.equals(y);
      if (this.Branch != null || other.Branch != null) {
        if (this.Branch == null && other.Branch != null) return false;
        if (this.Branch != null && other.Branch == null) return false;
        if (!f.apply(this.Branch, other.Branch)) return false;
      }
    }
    {
      Function3<String, String, Boolean> f = (x, y) -> x.equals(y);
      if (this.Tag != null || other.Tag != null) {
        if (this.Tag == null && other.Tag != null) return false;
        if (this.Tag != null && other.Tag == null) return false;
        if (!f.apply(this.Tag, other.Tag)) return false;
      }
    }
    {
      Function3<Boolean, Boolean, Boolean> f = (x, y) -> x == y;
      if (this.IsDirty != null || other.IsDirty != null) {
        if (this.IsDirty == null && other.IsDirty != null) return false;
        if (this.IsDirty != null && other.IsDirty == null) return false;
        if (!f.apply(this.IsDirty, other.IsDirty)) return false;
      }
    }
    return true;
  }

  public GitCodeBlob setRepo(String value) {
    this.Repo = value;
    return this;
  }

  public GitCodeBlob setHash(String value) {
    this.Hash = value;
    return this;
  }

  public GitCodeBlob setBranch(String value) {
    this.Branch = value;
    return this;
  }

  public GitCodeBlob setTag(String value) {
    this.Tag = value;
    return this;
  }

  public GitCodeBlob setIsDirty(Boolean value) {
    this.IsDirty = value;
    return this;
  }

  public static GitCodeBlob fromProto(ai.verta.modeldb.versioning.GitCodeBlob blob) {
    if (blob == null) {
      return null;
    }

    GitCodeBlob obj = new GitCodeBlob();
    {
      Function<ai.verta.modeldb.versioning.GitCodeBlob, String> f = x -> (blob.getRepo());
      obj.Repo = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.GitCodeBlob, String> f = x -> (blob.getHash());
      obj.Hash = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.GitCodeBlob, String> f = x -> (blob.getBranch());
      obj.Branch = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.GitCodeBlob, String> f = x -> (blob.getTag());
      obj.Tag = Utils.removeEmpty(f.apply(blob));
    }
    {
      Function<ai.verta.modeldb.versioning.GitCodeBlob, Boolean> f = x -> (blob.getIsDirty());
      obj.IsDirty = Utils.removeEmpty(f.apply(blob));
    }
    return obj;
  }

  public ai.verta.modeldb.versioning.GitCodeBlob.Builder toProto() {
    ai.verta.modeldb.versioning.GitCodeBlob.Builder builder =
        ai.verta.modeldb.versioning.GitCodeBlob.newBuilder();
    {
      if (this.Repo != null) {
        Function<ai.verta.modeldb.versioning.GitCodeBlob.Builder, Void> f =
            x -> {
              builder.setRepo(this.Repo);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Hash != null) {
        Function<ai.verta.modeldb.versioning.GitCodeBlob.Builder, Void> f =
            x -> {
              builder.setHash(this.Hash);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Branch != null) {
        Function<ai.verta.modeldb.versioning.GitCodeBlob.Builder, Void> f =
            x -> {
              builder.setBranch(this.Branch);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.Tag != null) {
        Function<ai.verta.modeldb.versioning.GitCodeBlob.Builder, Void> f =
            x -> {
              builder.setTag(this.Tag);
              return null;
            };
        f.apply(builder);
      }
    }
    {
      if (this.IsDirty != null) {
        Function<ai.verta.modeldb.versioning.GitCodeBlob.Builder, Void> f =
            x -> {
              builder.setIsDirty(this.IsDirty);
              return null;
            };
        f.apply(builder);
      }
    }
    return builder;
  }

  public void preVisitShallow(Visitor visitor) throws ModelDBException {
    visitor.preVisitGitCodeBlob(this);
  }

  public void preVisitDeep(Visitor visitor) throws ModelDBException {
    this.preVisitShallow(visitor);
    visitor.preVisitDeepString(this.Repo);
    visitor.preVisitDeepString(this.Hash);
    visitor.preVisitDeepString(this.Branch);
    visitor.preVisitDeepString(this.Tag);
    visitor.preVisitDeepBoolean(this.IsDirty);
  }

  public GitCodeBlob postVisitShallow(Visitor visitor) throws ModelDBException {
    return visitor.postVisitGitCodeBlob(this);
  }

  public GitCodeBlob postVisitDeep(Visitor visitor) throws ModelDBException {
    this.Repo = visitor.postVisitDeepString(this.Repo);
    this.Hash = visitor.postVisitDeepString(this.Hash);
    this.Branch = visitor.postVisitDeepString(this.Branch);
    this.Tag = visitor.postVisitDeepString(this.Tag);
    this.IsDirty = visitor.postVisitDeepBoolean(this.IsDirty);
    return this.postVisitShallow(visitor);
  }
}
