package hm.orz.chaos114.gae.rsspocket.meta.user;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-03-31 21:53:08")
/** */
public final class TokensMeta extends org.slim3.datastore.ModelMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens, java.util.Date> createdDate = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens, java.util.Date>(this, "createdDate", "createdDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens> readerToken = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens>(this, "readerToken", "readerToken");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens> userId = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens>(this, "userId", "userId");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.user.Tokens, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final TokensMeta slim3_singleton = new TokensMeta();

    /**
     * @return the singleton
     */
    public static TokensMeta get() {
       return slim3_singleton;
    }

    /** */
    public TokensMeta() {
        super("Tokens", hm.orz.chaos114.gae.rsspocket.model.user.Tokens.class);
    }

    @Override
    public hm.orz.chaos114.gae.rsspocket.model.user.Tokens entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens model = new hm.orz.chaos114.gae.rsspocket.model.user.Tokens();
        model.setCreatedDate((java.util.Date) entity.getProperty("createdDate"));
        model.setKey(entity.getKey());
        model.setReaderToken((java.lang.String) entity.getProperty("readerToken"));
        model.setUserId((java.lang.String) entity.getProperty("userId"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = (hm.orz.chaos114.gae.rsspocket.model.user.Tokens) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("createdDate", m.getCreatedDate());
        entity.setProperty("readerToken", m.getReaderToken());
        entity.setProperty("userId", m.getUserId());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = (hm.orz.chaos114.gae.rsspocket.model.user.Tokens) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = (hm.orz.chaos114.gae.rsspocket.model.user.Tokens) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = (hm.orz.chaos114.gae.rsspocket.model.user.Tokens) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = (hm.orz.chaos114.gae.rsspocket.model.user.Tokens) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = (hm.orz.chaos114.gae.rsspocket.model.user.Tokens) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCreatedDate() != null){
            writer.setNextPropertyName("createdDate");
            encoder0.encode(writer, m.getCreatedDate());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getReaderToken() != null){
            writer.setNextPropertyName("readerToken");
            encoder0.encode(writer, m.getReaderToken());
        }
        if(m.getUserId() != null){
            writer.setNextPropertyName("userId");
            encoder0.encode(writer, m.getUserId());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected hm.orz.chaos114.gae.rsspocket.model.user.Tokens jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.user.Tokens m = new hm.orz.chaos114.gae.rsspocket.model.user.Tokens();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("createdDate");
        m.setCreatedDate(decoder0.decode(reader, m.getCreatedDate()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("readerToken");
        m.setReaderToken(decoder0.decode(reader, m.getReaderToken()));
        reader = rootReader.newObjectReader("userId");
        m.setUserId(decoder0.decode(reader, m.getUserId()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}