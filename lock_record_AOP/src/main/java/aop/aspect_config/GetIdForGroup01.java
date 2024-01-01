package aop.aspect_config;

import java.util.UUID;

public class GetIdForGroup01 extends GetIdHelper {
  public UUID getRecordId(Object[] args) {
    return UUID.fromString(args[5].toString());
  };

}
