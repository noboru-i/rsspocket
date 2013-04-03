package hm.orz.chaos114.gae.rsspocket.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-03 23:52:46")
/** */
public final class UserInfoMeta extends org.slim3.datastore.ModelMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo> pocketAccessToken = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo>(this, "pocketAccessToken", "pocketAccessToken");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo, com.google.appengine.api.users.User> user = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo, com.google.appengine.api.users.User>(this, "user", "user", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserInfo, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final UserInfoMeta slim3_singleton = new UserInfoMeta();

    /**
     * @return the singleton
     */
    public static UserInfoMeta get() {
       return slim3_singleton;
    }

    /** */
    public UserInfoMeta() {
        super("UserInfo", hm.orz.chaos114.gae.rsspocket.model.UserInfo.class);
    }

    @Override
    public hm.orz.chaos114.gae.rsspocket.model.UserInfo entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo model = new hm.orz.chaos114.gae.rsspocket.model.UserInfo();
        model.setKey(entity.getKey());
        model.setPocketAccessToken(decrypt((java.lang.String)entity.getProperty("pocketAccessToken")));
        model.setUser((com.google.appengine.api.users.User) entity.getProperty("user"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = (hm.orz.chaos114.gae.rsspocket.model.UserInfo) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("pocketAccessToken", encrypt(m.getPocketAccessToken()));
        entity.setProperty("user", m.getUser());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = (hm.orz.chaos114.gae.rsspocket.model.UserInfo) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = (hm.orz.chaos114.gae.rsspocket.model.UserInfo) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = (hm.orz.chaos114.gae.rsspocket.model.UserInfo) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = (hm.orz.chaos114.gae.rsspocket.model.UserInfo) model;
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
        if ("pocketAccessToken".equals(propertyName)) return true;
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = (hm.orz.chaos114.gae.rsspocket.model.UserInfo) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getPocketAccessToken() != null){
            writer.setNextPropertyName("pocketAccessToken");
            encoder0.encode(writer, encrypt(m.getPocketAccessToken()));
        }
        if(m.getUser() != null){
            writer.setNextPropertyName("user");
            encoder0.encode(writer, m.getUser());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected hm.orz.chaos114.gae.rsspocket.model.UserInfo jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.UserInfo m = new hm.orz.chaos114.gae.rsspocket.model.UserInfo();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("pocketAccessToken");
        if(reader.read() != null){
            reader = new org.slim3.datastore.json.JsonValueReader(decrypt(reader.read()), rootReader.getModelReader());
        }
        m.setPocketAccessToken(decoder0.decode(reader, m.getPocketAccessToken()));
        reader = rootReader.newObjectReader("user");
        m.setUser(decoder0.decode(reader, m.getUser()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}