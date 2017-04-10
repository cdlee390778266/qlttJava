// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Bpem.proto

package com.qlcd.qltt.body;

public final class Bpem {
  private Bpem() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code com.qlcd.qltt.body.Bpem.PEB_TTACCTSTAT}
   */
  public enum PEB_TTACCTSTAT
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EV_NORMAL = 0;</code>
     */
    EV_NORMAL(0, 0),
    /**
     * <code>EV_FREEZE = 1;</code>
     */
    EV_FREEZE(1, 1),
    /**
     * <code>EV_CANCEL = 2;</code>
     */
    EV_CANCEL(2, 2),
    UNRECOGNIZED(-1, -1),
    ;

    /**
     * <code>EV_NORMAL = 0;</code>
     */
    public static final int EV_NORMAL_VALUE = 0;
    /**
     * <code>EV_FREEZE = 1;</code>
     */
    public static final int EV_FREEZE_VALUE = 1;
    /**
     * <code>EV_CANCEL = 2;</code>
     */
    public static final int EV_CANCEL_VALUE = 2;


    public final int getNumber() {
      if (index == -1) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    public static PEB_TTACCTSTAT valueOf(int value) {
      switch (value) {
        case 0: return EV_NORMAL;
        case 1: return EV_FREEZE;
        case 2: return EV_CANCEL;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PEB_TTACCTSTAT>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<PEB_TTACCTSTAT>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PEB_TTACCTSTAT>() {
            public PEB_TTACCTSTAT findValueByNumber(int number) {
              return PEB_TTACCTSTAT.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.qlcd.qltt.body.Bpem.getDescriptor().getEnumTypes().get(0);
    }

    private static final PEB_TTACCTSTAT[] VALUES = values();

    public static PEB_TTACCTSTAT valueOf(
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

    private final int index;
    private final int value;

    private PEB_TTACCTSTAT(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.qlcd.qltt.body.Bpem.PEB_TTACCTSTAT)
  }

  /**
   * Protobuf enum {@code com.qlcd.qltt.body.Bpem.PEB_SVCCHNL}
   */
  public enum PEB_SVCCHNL
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EV_UNKNOWN = 0;</code>
     */
    EV_UNKNOWN(0, 0),
    /**
     * <code>EV_WXCHNL = 1;</code>
     */
    EV_WXCHNL(1, 1),
    /**
     * <code>EV_CPCHNL = 2;</code>
     */
    EV_CPCHNL(2, 2),
    UNRECOGNIZED(-1, -1),
    ;

    /**
     * <code>EV_UNKNOWN = 0;</code>
     */
    public static final int EV_UNKNOWN_VALUE = 0;
    /**
     * <code>EV_WXCHNL = 1;</code>
     */
    public static final int EV_WXCHNL_VALUE = 1;
    /**
     * <code>EV_CPCHNL = 2;</code>
     */
    public static final int EV_CPCHNL_VALUE = 2;


    public final int getNumber() {
      if (index == -1) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    public static PEB_SVCCHNL valueOf(int value) {
      switch (value) {
        case 0: return EV_UNKNOWN;
        case 1: return EV_WXCHNL;
        case 2: return EV_CPCHNL;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PEB_SVCCHNL>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<PEB_SVCCHNL>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PEB_SVCCHNL>() {
            public PEB_SVCCHNL findValueByNumber(int number) {
              return PEB_SVCCHNL.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.qlcd.qltt.body.Bpem.getDescriptor().getEnumTypes().get(1);
    }

    private static final PEB_SVCCHNL[] VALUES = values();

    public static PEB_SVCCHNL valueOf(
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

    private final int index;
    private final int value;

    private PEB_SVCCHNL(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.qlcd.qltt.body.Bpem.PEB_SVCCHNL)
  }

  /**
   * Protobuf enum {@code com.qlcd.qltt.body.Bpem.PEB_DEVTYPE}
   */
  public enum PEB_DEVTYPE
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EV_NODEV = 0;</code>
     */
    EV_NODEV(0, 0),
    /**
     * <code>EV_IOS = 1;</code>
     */
    EV_IOS(1, 1),
    /**
     * <code>EV_ANDROID = 2;</code>
     */
    EV_ANDROID(2, 2),
    UNRECOGNIZED(-1, -1),
    ;

    /**
     * <code>EV_NODEV = 0;</code>
     */
    public static final int EV_NODEV_VALUE = 0;
    /**
     * <code>EV_IOS = 1;</code>
     */
    public static final int EV_IOS_VALUE = 1;
    /**
     * <code>EV_ANDROID = 2;</code>
     */
    public static final int EV_ANDROID_VALUE = 2;


    public final int getNumber() {
      if (index == -1) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    public static PEB_DEVTYPE valueOf(int value) {
      switch (value) {
        case 0: return EV_NODEV;
        case 1: return EV_IOS;
        case 2: return EV_ANDROID;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PEB_DEVTYPE>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<PEB_DEVTYPE>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PEB_DEVTYPE>() {
            public PEB_DEVTYPE findValueByNumber(int number) {
              return PEB_DEVTYPE.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.qlcd.qltt.body.Bpem.getDescriptor().getEnumTypes().get(2);
    }

    private static final PEB_DEVTYPE[] VALUES = values();

    public static PEB_DEVTYPE valueOf(
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

    private final int index;
    private final int value;

    private PEB_DEVTYPE(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.qlcd.qltt.body.Bpem.PEB_DEVTYPE)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nBpem.proto\022\027com.qlcd.qltt.body.Bpem*=\n" +
      "\016PEB_TTACCTSTAT\022\r\n\tEV_NORMAL\020\000\022\r\n\tEV_FRE" +
      "EZE\020\001\022\r\n\tEV_CANCEL\020\002*;\n\013PEB_SVCCHNL\022\016\n\nE" +
      "V_UNKNOWN\020\000\022\r\n\tEV_WXCHNL\020\001\022\r\n\tEV_CPCHNL\020" +
      "\002*7\n\013PEB_DEVTYPE\022\014\n\010EV_NODEV\020\000\022\n\n\006EV_IOS" +
      "\020\001\022\016\n\nEV_ANDROID\020\002B\024\n\022com.qlcd.qltt.body" +
      "b\006proto3"
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
