// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: head/Hpem.proto

package com.qlcd.qltt.head;

public final class Hpem {
  private Hpem() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS}
   */
  public enum PEH_RSPSTATUS
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EV_SUCCESS = 0;</code>
     */
    EV_SUCCESS(0),
    /**
     * <code>EV_FAILURE = 1;</code>
     */
    EV_FAILURE(1),
    /**
     * <code>EV_EXCEPTION = 2;</code>
     */
    EV_EXCEPTION(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>EV_SUCCESS = 0;</code>
     */
    public static final int EV_SUCCESS_VALUE = 0;
    /**
     * <code>EV_FAILURE = 1;</code>
     */
    public static final int EV_FAILURE_VALUE = 1;
    /**
     * <code>EV_EXCEPTION = 2;</code>
     */
    public static final int EV_EXCEPTION_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static PEH_RSPSTATUS valueOf(int value) {
      return forNumber(value);
    }

    public static PEH_RSPSTATUS forNumber(int value) {
      switch (value) {
        case 0: return EV_SUCCESS;
        case 1: return EV_FAILURE;
        case 2: return EV_EXCEPTION;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PEH_RSPSTATUS>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        PEH_RSPSTATUS> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PEH_RSPSTATUS>() {
            public PEH_RSPSTATUS findValueByNumber(int number) {
              return PEH_RSPSTATUS.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.qlcd.qltt.head.Hpem.getDescriptor().getEnumTypes().get(0);
    }

    private static final PEH_RSPSTATUS[] VALUES = values();

    public static PEH_RSPSTATUS valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private PEH_RSPSTATUS(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.qlcd.qltt.head.Hpem.PEH_RSPSTATUS)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017head/Hpem.proto\022\027com.qlcd.qltt.head.Hp" +
      "em*A\n\rPEH_RSPSTATUS\022\016\n\nEV_SUCCESS\020\000\022\016\n\nE" +
      "V_FAILURE\020\001\022\020\n\014EV_EXCEPTION\020\002B\024\n\022com.qlc" +
      "d.qltt.headb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
