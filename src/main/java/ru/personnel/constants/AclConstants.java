package ru.personnel.constants;

import lombok.Getter;

public class AclConstants {

    @Getter
    public enum ResourceAccessKind {
        WRITE("rw"),
        READ("ro");

        private final String accessCode;
        ResourceAccessKind(String accessCode) {
            this.accessCode = accessCode;
        }
    }

    @Getter
    public enum ResourceAccess {
        WRITE("write"),
        READ("read");

        private final String access;
        ResourceAccess(String access) {
            this.access = access;
        }
    }

    @Getter
    public enum ResourceCode {
        PROJECT_MANAGEMENT("project_management");

        private final String resource;
        ResourceCode(String resource) {
            this.resource = resource;
        }
    }

    public static class User {
        public static class StatusCode {
            public static final String ACTIVE = "active";
            public static final String DELETED = "deleted";
        }
    }

    public static class Role {
        public static class StatusCode {
            public static final String ACTIVE = "active";
            public static final String DELETED = "deleted";
        }
    }


}
