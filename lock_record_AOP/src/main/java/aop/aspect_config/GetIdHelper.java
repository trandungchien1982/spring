package aop.aspect_config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

public class GetIdHelper {
  public UUID getRecordId(Object[] args) {
    Object parseUUID = args[0];
    if (parseUUID instanceof UUID) {
      return (UUID)parseUUID;
    }
    return UUID.fromString("");
  };

}
