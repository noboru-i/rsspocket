package hm.orz.chaos114.gae.rsspocket.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-08 23:57:17")
/** */
public final class UserRssMeta extends org.slim3.datastore.ModelMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, org.slim3.datastore.ModelRef<hm.orz.chaos114.gae.rsspocket.model.RssFeed>, hm.orz.chaos114.gae.rsspocket.model.RssFeed> rssFeed = new org.slim3.datastore.ModelRefAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, org.slim3.datastore.ModelRef<hm.orz.chaos114.gae.rsspocket.model.RssFeed>, hm.orz.chaos114.gae.rsspocket.model.RssFeed>(this, "rssFeed", "rssFeed", org.slim3.datastore.ModelRef.class, hm.orz.chaos114.gae.rsspocket.model.RssFeed.class);

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, java.util.Set<java.lang.String>> tags = new org.slim3.datastore.StringCollectionAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, java.util.Set<java.lang.String>>(this, "tags", "tags", java.util.Set.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, com.google.appengine.api.users.User> user = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, com.google.appengine.api.users.User>(this, "user", "user", com.google.appengine.api.users.User.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.UserRss, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final UserRssMeta slim3_singleton = new UserRssMeta();

    /**
     * @return the singleton
     */
    public static UserRssMeta get() {
       return slim3_singleton;
    }

    /** */
    public UserRssMeta() {
        super("UserRss", hm.orz.chaos114.gae.rsspocket.model.UserRss.class);
    }

    @Override
    public hm.orz.chaos114.gae.rsspocket.model.UserRss entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss model = new hm.orz.chaos114.gae.rsspocket.model.UserRss();
        model.setKey(entity.getKey());
        if (model.getRssFeed() == null) {
            throw new NullPointerException("The property(rssFeed) is null.");
        }
        model.getRssFeed().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("rssFeed"));
        model.setTags(new java.util.HashSet<java.lang.String>(toList(java.lang.String.class, entity.getProperty("tags"))));
        model.setUser((com.google.appengine.api.users.User) entity.getProperty("user"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        if (m.getRssFeed() == null) {
            throw new NullPointerException("The property(rssFeed) must not be null.");
        }
        entity.setProperty("rssFeed", m.getRssFeed().getKey());
        entity.setProperty("tags", m.getTags());
        entity.setProperty("user", m.getUser());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
        if (m.getRssFeed() == null) {
            throw new NullPointerException("The property(rssFeed) must not be null.");
        }
        m.getRssFeed().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
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
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = (hm.orz.chaos114.gae.rsspocket.model.UserRss) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getRssFeed() != null && m.getRssFeed().getKey() != null){
            writer.setNextPropertyName("rssFeed");
            encoder0.encode(writer, m.getRssFeed(), maxDepth, currentDepth);
        }
        if(m.getTags() != null){
            writer.setNextPropertyName("tags");
            writer.beginArray();
            for(java.lang.String v : m.getTags()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
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
    protected hm.orz.chaos114.gae.rsspocket.model.UserRss jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.UserRss m = new hm.orz.chaos114.gae.rsspocket.model.UserRss();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("rssFeed");
        decoder0.decode(reader, m.getRssFeed(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("tags");
        {
            java.util.HashSet<java.lang.String> elements = new java.util.HashSet<java.lang.String>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("tags");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.String v = decoder0.decode(reader, (java.lang.String)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setTags(elements);
            }
        }
        reader = rootReader.newObjectReader("user");
        m.setUser(decoder0.decode(reader, m.getUser()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}