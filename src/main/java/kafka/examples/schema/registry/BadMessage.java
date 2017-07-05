/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package kafka.examples.schema.registry;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class BadMessage extends org.apache.avro.specific.SpecificRecordBase implements
    org.apache.avro.specific.SpecificRecord {

  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse(
      "{\"type\":\"record\",\"name\":\"BadMessage\",\"namespace\":\"kafka.examples.schema.registry\",\"fields\":[{\"name\":\"f2\",\"type\":\"int\"}]}");
  private static final long serialVersionUID = 7230592356170060830L;
  private static SpecificData MODEL$ = new SpecificData();
  private static final BinaryMessageEncoder<BadMessage> ENCODER = new BinaryMessageEncoder<BadMessage>(MODEL$, SCHEMA$);
  private static final BinaryMessageDecoder<BadMessage> DECODER = new BinaryMessageDecoder<BadMessage>(MODEL$, SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<BadMessage> WRITER$ = (org.apache.avro.io.DatumWriter<BadMessage>) MODEL$
      .createDatumWriter(SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BadMessage> READER$ = (org.apache.avro.io.DatumReader<BadMessage>) MODEL$
      .createDatumReader(SCHEMA$);
  @Deprecated
  public int f2;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BadMessage() {
  }

  /**
   * All-args constructor.
   *
   * @param f2 The new value for f2
   */
  public BadMessage(java.lang.Integer f2) {
    this.f2 = f2;
  }

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<BadMessage> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   *
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<BadMessage> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<BadMessage>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Deserializes a BadMessage from a ByteBuffer.
   */
  public static BadMessage fromByteBuffer(java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /**
   * Creates a new BadMessage RecordBuilder.
   *
   * @return A new BadMessage RecordBuilder
   */
  public static kafka.examples.schema.registry.BadMessage.Builder newBuilder() {
    return new kafka.examples.schema.registry.BadMessage.Builder();
  }

  /**
   * Creates a new BadMessage RecordBuilder by copying an existing Builder.
   *
   * @param other The existing builder to copy.
   * @return A new BadMessage RecordBuilder
   */
  public static kafka.examples.schema.registry.BadMessage.Builder newBuilder(
      kafka.examples.schema.registry.BadMessage.Builder other) {
    return new kafka.examples.schema.registry.BadMessage.Builder(other);
  }

  /**
   * Creates a new BadMessage RecordBuilder by copying an existing BadMessage instance.
   *
   * @param other The existing instance to copy.
   * @return A new BadMessage RecordBuilder
   */
  public static kafka.examples.schema.registry.BadMessage.Builder newBuilder(
      kafka.examples.schema.registry.BadMessage other) {
    return new kafka.examples.schema.registry.BadMessage.Builder(other);
  }

  /**
   * Serializes this BadMessage to a ByteBuffer.
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }

  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0:
        return f2;
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0:
        f2 = (java.lang.Integer) value$;
        break;
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'f2' field.
   *
   * @return The value of the 'f2' field.
   */
  public java.lang.Integer getF2() {
    return f2;
  }

  /**
   * Sets the value of the 'f2' field.
   *
   * @param value the value to set.
   */
  public void setF2(java.lang.Integer value) {
    this.f2 = value;
  }

  @Override
  public void writeExternal(java.io.ObjectOutput out) throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @Override
  public void readExternal(java.io.ObjectInput in) throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  /**
   * RecordBuilder for BadMessage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BadMessage> implements
      org.apache.avro.data.RecordBuilder<BadMessage> {

    private int f2;

    /**
     * Creates a new Builder
     */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     *
     * @param other The existing Builder to copy.
     */
    private Builder(kafka.examples.schema.registry.BadMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.f2)) {
        this.f2 = data().deepCopy(fields()[0].schema(), other.f2);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing BadMessage instance
     *
     * @param other The existing instance to copy.
     */
    private Builder(kafka.examples.schema.registry.BadMessage other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.f2)) {
        this.f2 = data().deepCopy(fields()[0].schema(), other.f2);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Gets the value of the 'f2' field.
     *
     * @return The value.
     */
    public java.lang.Integer getF2() {
      return f2;
    }

    /**
     * Sets the value of the 'f2' field.
     *
     * @param value The value of 'f2'.
     * @return This builder.
     */
    public kafka.examples.schema.registry.BadMessage.Builder setF2(int value) {
      validate(fields()[0], value);
      this.f2 = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'f2' field has been set.
     *
     * @return True if the 'f2' field has been set, false otherwise.
     */
    public boolean hasF2() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'f2' field.
     *
     * @return This builder.
     */
    public kafka.examples.schema.registry.BadMessage.Builder clearF2() {
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BadMessage build() {
      try {
        BadMessage record = new BadMessage();
        record.f2 = fieldSetFlags()[0] ? this.f2 : (java.lang.Integer) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

}
