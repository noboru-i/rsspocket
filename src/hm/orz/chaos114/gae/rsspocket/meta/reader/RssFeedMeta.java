package hm.orz.chaos114.gae.rsspocket.meta.reader;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-02 00:38:07")
/** */
public final class RssFeedMeta extends org.slim3.datastore.ModelMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed> rssUrl = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed>(this, "rssUrl", "rssUrl");

    /** */
    public final org.slim3.datastore.StringCollectionAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed, java.util.Set<java.lang.String>> tags = new org.slim3.datastore.StringCollectionAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed, java.util.Set<java.lang.String>>(this, "tags", "tags", java.util.Set.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final RssFeedMeta slim3_singleton = new RssFeedMeta();

    /**
     * @return the singleton
     */
    public static RssFeedMeta get() {
       return slim3_singleton;
    }

    /** */
    public RssFeedMeta() {
        super("RssFeed", hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed.class);
    }

    @Override
    public hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed model = new hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed();
        model.setKey(entity.getKey());
        model.setRssUrl((java.lang.String) entity.getProperty("rssUrl"));
        model.setTags(new java.util.HashSet<java.lang.String>(toList(java.lang.String.class, entity.getProperty("tags"))));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("rssUrl", m.getRssUrl());
        entity.setProperty("tags", m.getTags());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed) model;
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
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getRssUrl() != null){
            writer.setNextPropertyName("rssUrl");
            encoder0.encode(writer, m.getRssUrl());
        }
        if(m.getTags() != null){
            writer.setNextPropertyName("tags");
            writer.beginArray();
            for(java.lang.String v : m.getTags()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed m = new hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("rssUrl");
        m.setRssUrl(decoder0.decode(reader, m.getRssUrl()));
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
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}