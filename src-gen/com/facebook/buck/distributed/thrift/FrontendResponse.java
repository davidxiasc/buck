/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.facebook.buck.distributed.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)")
public class FrontendResponse implements org.apache.thrift.TBase<FrontendResponse, FrontendResponse._Fields>, java.io.Serializable, Cloneable, Comparable<FrontendResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FrontendResponse");

  private static final org.apache.thrift.protocol.TField WAS_SUCCESSFUL_FIELD_DESC = new org.apache.thrift.protocol.TField("wasSuccessful", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField ERROR_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("errorMessage", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)10);
  private static final org.apache.thrift.protocol.TField ANNOUNCEMENT_RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("announcementResponse", org.apache.thrift.protocol.TType.STRUCT, (short)19);
  private static final org.apache.thrift.protocol.TField FETCH_RULE_KEY_LOGS_RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("fetchRuleKeyLogsResponse", org.apache.thrift.protocol.TType.STRUCT, (short)26);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new FrontendResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new FrontendResponseTupleSchemeFactory();

  public boolean wasSuccessful; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String errorMessage; // optional
  /**
   * 
   * @see FrontendRequestType
   */
  public @org.apache.thrift.annotation.Nullable FrontendRequestType type; // optional
  public @org.apache.thrift.annotation.Nullable AnnouncementResponse announcementResponse; // optional
  public @org.apache.thrift.annotation.Nullable FetchRuleKeyLogsResponse fetchRuleKeyLogsResponse; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WAS_SUCCESSFUL((short)1, "wasSuccessful"),
    ERROR_MESSAGE((short)2, "errorMessage"),
    /**
     * 
     * @see FrontendRequestType
     */
    TYPE((short)10, "type"),
    ANNOUNCEMENT_RESPONSE((short)19, "announcementResponse"),
    FETCH_RULE_KEY_LOGS_RESPONSE((short)26, "fetchRuleKeyLogsResponse");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // WAS_SUCCESSFUL
          return WAS_SUCCESSFUL;
        case 2: // ERROR_MESSAGE
          return ERROR_MESSAGE;
        case 10: // TYPE
          return TYPE;
        case 19: // ANNOUNCEMENT_RESPONSE
          return ANNOUNCEMENT_RESPONSE;
        case 26: // FETCH_RULE_KEY_LOGS_RESPONSE
          return FETCH_RULE_KEY_LOGS_RESPONSE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __WASSUCCESSFUL_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.WAS_SUCCESSFUL,_Fields.ERROR_MESSAGE,_Fields.TYPE,_Fields.ANNOUNCEMENT_RESPONSE,_Fields.FETCH_RULE_KEY_LOGS_RESPONSE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.WAS_SUCCESSFUL, new org.apache.thrift.meta_data.FieldMetaData("wasSuccessful", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ERROR_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("errorMessage", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, FrontendRequestType.class)));
    tmpMap.put(_Fields.ANNOUNCEMENT_RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("announcementResponse", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, AnnouncementResponse.class)));
    tmpMap.put(_Fields.FETCH_RULE_KEY_LOGS_RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("fetchRuleKeyLogsResponse", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, FetchRuleKeyLogsResponse.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FrontendResponse.class, metaDataMap);
  }

  public FrontendResponse() {
    this.type = com.facebook.buck.distributed.thrift.FrontendRequestType.UNKNOWN;

  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FrontendResponse(FrontendResponse other) {
    __isset_bitfield = other.__isset_bitfield;
    this.wasSuccessful = other.wasSuccessful;
    if (other.isSetErrorMessage()) {
      this.errorMessage = other.errorMessage;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetAnnouncementResponse()) {
      this.announcementResponse = new AnnouncementResponse(other.announcementResponse);
    }
    if (other.isSetFetchRuleKeyLogsResponse()) {
      this.fetchRuleKeyLogsResponse = new FetchRuleKeyLogsResponse(other.fetchRuleKeyLogsResponse);
    }
  }

  public FrontendResponse deepCopy() {
    return new FrontendResponse(this);
  }

  @Override
  public void clear() {
    setWasSuccessfulIsSet(false);
    this.wasSuccessful = false;
    this.errorMessage = null;
    this.type = com.facebook.buck.distributed.thrift.FrontendRequestType.UNKNOWN;

    this.announcementResponse = null;
    this.fetchRuleKeyLogsResponse = null;
  }

  public boolean isWasSuccessful() {
    return this.wasSuccessful;
  }

  public FrontendResponse setWasSuccessful(boolean wasSuccessful) {
    this.wasSuccessful = wasSuccessful;
    setWasSuccessfulIsSet(true);
    return this;
  }

  public void unsetWasSuccessful() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __WASSUCCESSFUL_ISSET_ID);
  }

  /** Returns true if field wasSuccessful is set (has been assigned a value) and false otherwise */
  public boolean isSetWasSuccessful() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __WASSUCCESSFUL_ISSET_ID);
  }

  public void setWasSuccessfulIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __WASSUCCESSFUL_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getErrorMessage() {
    return this.errorMessage;
  }

  public FrontendResponse setErrorMessage(@org.apache.thrift.annotation.Nullable java.lang.String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  public void unsetErrorMessage() {
    this.errorMessage = null;
  }

  /** Returns true if field errorMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetErrorMessage() {
    return this.errorMessage != null;
  }

  public void setErrorMessageIsSet(boolean value) {
    if (!value) {
      this.errorMessage = null;
    }
  }

  /**
   * 
   * @see FrontendRequestType
   */
  @org.apache.thrift.annotation.Nullable
  public FrontendRequestType getType() {
    return this.type;
  }

  /**
   * 
   * @see FrontendRequestType
   */
  public FrontendResponse setType(@org.apache.thrift.annotation.Nullable FrontendRequestType type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public AnnouncementResponse getAnnouncementResponse() {
    return this.announcementResponse;
  }

  public FrontendResponse setAnnouncementResponse(@org.apache.thrift.annotation.Nullable AnnouncementResponse announcementResponse) {
    this.announcementResponse = announcementResponse;
    return this;
  }

  public void unsetAnnouncementResponse() {
    this.announcementResponse = null;
  }

  /** Returns true if field announcementResponse is set (has been assigned a value) and false otherwise */
  public boolean isSetAnnouncementResponse() {
    return this.announcementResponse != null;
  }

  public void setAnnouncementResponseIsSet(boolean value) {
    if (!value) {
      this.announcementResponse = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public FetchRuleKeyLogsResponse getFetchRuleKeyLogsResponse() {
    return this.fetchRuleKeyLogsResponse;
  }

  public FrontendResponse setFetchRuleKeyLogsResponse(@org.apache.thrift.annotation.Nullable FetchRuleKeyLogsResponse fetchRuleKeyLogsResponse) {
    this.fetchRuleKeyLogsResponse = fetchRuleKeyLogsResponse;
    return this;
  }

  public void unsetFetchRuleKeyLogsResponse() {
    this.fetchRuleKeyLogsResponse = null;
  }

  /** Returns true if field fetchRuleKeyLogsResponse is set (has been assigned a value) and false otherwise */
  public boolean isSetFetchRuleKeyLogsResponse() {
    return this.fetchRuleKeyLogsResponse != null;
  }

  public void setFetchRuleKeyLogsResponseIsSet(boolean value) {
    if (!value) {
      this.fetchRuleKeyLogsResponse = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case WAS_SUCCESSFUL:
      if (value == null) {
        unsetWasSuccessful();
      } else {
        setWasSuccessful((java.lang.Boolean)value);
      }
      break;

    case ERROR_MESSAGE:
      if (value == null) {
        unsetErrorMessage();
      } else {
        setErrorMessage((java.lang.String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((FrontendRequestType)value);
      }
      break;

    case ANNOUNCEMENT_RESPONSE:
      if (value == null) {
        unsetAnnouncementResponse();
      } else {
        setAnnouncementResponse((AnnouncementResponse)value);
      }
      break;

    case FETCH_RULE_KEY_LOGS_RESPONSE:
      if (value == null) {
        unsetFetchRuleKeyLogsResponse();
      } else {
        setFetchRuleKeyLogsResponse((FetchRuleKeyLogsResponse)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case WAS_SUCCESSFUL:
      return isWasSuccessful();

    case ERROR_MESSAGE:
      return getErrorMessage();

    case TYPE:
      return getType();

    case ANNOUNCEMENT_RESPONSE:
      return getAnnouncementResponse();

    case FETCH_RULE_KEY_LOGS_RESPONSE:
      return getFetchRuleKeyLogsResponse();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case WAS_SUCCESSFUL:
      return isSetWasSuccessful();
    case ERROR_MESSAGE:
      return isSetErrorMessage();
    case TYPE:
      return isSetType();
    case ANNOUNCEMENT_RESPONSE:
      return isSetAnnouncementResponse();
    case FETCH_RULE_KEY_LOGS_RESPONSE:
      return isSetFetchRuleKeyLogsResponse();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof FrontendResponse)
      return this.equals((FrontendResponse)that);
    return false;
  }

  public boolean equals(FrontendResponse that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_wasSuccessful = true && this.isSetWasSuccessful();
    boolean that_present_wasSuccessful = true && that.isSetWasSuccessful();
    if (this_present_wasSuccessful || that_present_wasSuccessful) {
      if (!(this_present_wasSuccessful && that_present_wasSuccessful))
        return false;
      if (this.wasSuccessful != that.wasSuccessful)
        return false;
    }

    boolean this_present_errorMessage = true && this.isSetErrorMessage();
    boolean that_present_errorMessage = true && that.isSetErrorMessage();
    if (this_present_errorMessage || that_present_errorMessage) {
      if (!(this_present_errorMessage && that_present_errorMessage))
        return false;
      if (!this.errorMessage.equals(that.errorMessage))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_announcementResponse = true && this.isSetAnnouncementResponse();
    boolean that_present_announcementResponse = true && that.isSetAnnouncementResponse();
    if (this_present_announcementResponse || that_present_announcementResponse) {
      if (!(this_present_announcementResponse && that_present_announcementResponse))
        return false;
      if (!this.announcementResponse.equals(that.announcementResponse))
        return false;
    }

    boolean this_present_fetchRuleKeyLogsResponse = true && this.isSetFetchRuleKeyLogsResponse();
    boolean that_present_fetchRuleKeyLogsResponse = true && that.isSetFetchRuleKeyLogsResponse();
    if (this_present_fetchRuleKeyLogsResponse || that_present_fetchRuleKeyLogsResponse) {
      if (!(this_present_fetchRuleKeyLogsResponse && that_present_fetchRuleKeyLogsResponse))
        return false;
      if (!this.fetchRuleKeyLogsResponse.equals(that.fetchRuleKeyLogsResponse))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetWasSuccessful()) ? 131071 : 524287);
    if (isSetWasSuccessful())
      hashCode = hashCode * 8191 + ((wasSuccessful) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetErrorMessage()) ? 131071 : 524287);
    if (isSetErrorMessage())
      hashCode = hashCode * 8191 + errorMessage.hashCode();

    hashCode = hashCode * 8191 + ((isSetType()) ? 131071 : 524287);
    if (isSetType())
      hashCode = hashCode * 8191 + type.getValue();

    hashCode = hashCode * 8191 + ((isSetAnnouncementResponse()) ? 131071 : 524287);
    if (isSetAnnouncementResponse())
      hashCode = hashCode * 8191 + announcementResponse.hashCode();

    hashCode = hashCode * 8191 + ((isSetFetchRuleKeyLogsResponse()) ? 131071 : 524287);
    if (isSetFetchRuleKeyLogsResponse())
      hashCode = hashCode * 8191 + fetchRuleKeyLogsResponse.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(FrontendResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetWasSuccessful()).compareTo(other.isSetWasSuccessful());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWasSuccessful()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.wasSuccessful, other.wasSuccessful);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetErrorMessage()).compareTo(other.isSetErrorMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrorMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errorMessage, other.errorMessage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAnnouncementResponse()).compareTo(other.isSetAnnouncementResponse());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAnnouncementResponse()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.announcementResponse, other.announcementResponse);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFetchRuleKeyLogsResponse()).compareTo(other.isSetFetchRuleKeyLogsResponse());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFetchRuleKeyLogsResponse()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fetchRuleKeyLogsResponse, other.fetchRuleKeyLogsResponse);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("FrontendResponse(");
    boolean first = true;

    if (isSetWasSuccessful()) {
      sb.append("wasSuccessful:");
      sb.append(this.wasSuccessful);
      first = false;
    }
    if (isSetErrorMessage()) {
      if (!first) sb.append(", ");
      sb.append("errorMessage:");
      if (this.errorMessage == null) {
        sb.append("null");
      } else {
        sb.append(this.errorMessage);
      }
      first = false;
    }
    if (isSetType()) {
      if (!first) sb.append(", ");
      sb.append("type:");
      if (this.type == null) {
        sb.append("null");
      } else {
        sb.append(this.type);
      }
      first = false;
    }
    if (isSetAnnouncementResponse()) {
      if (!first) sb.append(", ");
      sb.append("announcementResponse:");
      if (this.announcementResponse == null) {
        sb.append("null");
      } else {
        sb.append(this.announcementResponse);
      }
      first = false;
    }
    if (isSetFetchRuleKeyLogsResponse()) {
      if (!first) sb.append(", ");
      sb.append("fetchRuleKeyLogsResponse:");
      if (this.fetchRuleKeyLogsResponse == null) {
        sb.append("null");
      } else {
        sb.append(this.fetchRuleKeyLogsResponse);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (announcementResponse != null) {
      announcementResponse.validate();
    }
    if (fetchRuleKeyLogsResponse != null) {
      fetchRuleKeyLogsResponse.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class FrontendResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FrontendResponseStandardScheme getScheme() {
      return new FrontendResponseStandardScheme();
    }
  }

  private static class FrontendResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<FrontendResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FrontendResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WAS_SUCCESSFUL
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.wasSuccessful = iprot.readBool();
              struct.setWasSuccessfulIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ERROR_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.errorMessage = iprot.readString();
              struct.setErrorMessageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = com.facebook.buck.distributed.thrift.FrontendRequestType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 19: // ANNOUNCEMENT_RESPONSE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.announcementResponse = new AnnouncementResponse();
              struct.announcementResponse.read(iprot);
              struct.setAnnouncementResponseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 26: // FETCH_RULE_KEY_LOGS_RESPONSE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.fetchRuleKeyLogsResponse = new FetchRuleKeyLogsResponse();
              struct.fetchRuleKeyLogsResponse.read(iprot);
              struct.setFetchRuleKeyLogsResponseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, FrontendResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetWasSuccessful()) {
        oprot.writeFieldBegin(WAS_SUCCESSFUL_FIELD_DESC);
        oprot.writeBool(struct.wasSuccessful);
        oprot.writeFieldEnd();
      }
      if (struct.errorMessage != null) {
        if (struct.isSetErrorMessage()) {
          oprot.writeFieldBegin(ERROR_MESSAGE_FIELD_DESC);
          oprot.writeString(struct.errorMessage);
          oprot.writeFieldEnd();
        }
      }
      if (struct.type != null) {
        if (struct.isSetType()) {
          oprot.writeFieldBegin(TYPE_FIELD_DESC);
          oprot.writeI32(struct.type.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.announcementResponse != null) {
        if (struct.isSetAnnouncementResponse()) {
          oprot.writeFieldBegin(ANNOUNCEMENT_RESPONSE_FIELD_DESC);
          struct.announcementResponse.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.fetchRuleKeyLogsResponse != null) {
        if (struct.isSetFetchRuleKeyLogsResponse()) {
          oprot.writeFieldBegin(FETCH_RULE_KEY_LOGS_RESPONSE_FIELD_DESC);
          struct.fetchRuleKeyLogsResponse.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FrontendResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FrontendResponseTupleScheme getScheme() {
      return new FrontendResponseTupleScheme();
    }
  }

  private static class FrontendResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<FrontendResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FrontendResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetWasSuccessful()) {
        optionals.set(0);
      }
      if (struct.isSetErrorMessage()) {
        optionals.set(1);
      }
      if (struct.isSetType()) {
        optionals.set(2);
      }
      if (struct.isSetAnnouncementResponse()) {
        optionals.set(3);
      }
      if (struct.isSetFetchRuleKeyLogsResponse()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetWasSuccessful()) {
        oprot.writeBool(struct.wasSuccessful);
      }
      if (struct.isSetErrorMessage()) {
        oprot.writeString(struct.errorMessage);
      }
      if (struct.isSetType()) {
        oprot.writeI32(struct.type.getValue());
      }
      if (struct.isSetAnnouncementResponse()) {
        struct.announcementResponse.write(oprot);
      }
      if (struct.isSetFetchRuleKeyLogsResponse()) {
        struct.fetchRuleKeyLogsResponse.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FrontendResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.wasSuccessful = iprot.readBool();
        struct.setWasSuccessfulIsSet(true);
      }
      if (incoming.get(1)) {
        struct.errorMessage = iprot.readString();
        struct.setErrorMessageIsSet(true);
      }
      if (incoming.get(2)) {
        struct.type = com.facebook.buck.distributed.thrift.FrontendRequestType.findByValue(iprot.readI32());
        struct.setTypeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.announcementResponse = new AnnouncementResponse();
        struct.announcementResponse.read(iprot);
        struct.setAnnouncementResponseIsSet(true);
      }
      if (incoming.get(4)) {
        struct.fetchRuleKeyLogsResponse = new FetchRuleKeyLogsResponse();
        struct.fetchRuleKeyLogsResponse.read(iprot);
        struct.setFetchRuleKeyLogsResponseIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

