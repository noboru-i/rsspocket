package hm.orz.chaos114.gae.rsspocket.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-16 00:42:45")
/** */
public final class RssFeedMeta extends org.slim3.datastore.ModelMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed, java.util.Date> latestPublishedDate = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed, java.util.Date>(this, "latestPublishedDate", "latestPublishedDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed> siteUrl = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed>(this, "siteUrl", "siteUrl");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed> title = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed>(this, "title", "title");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed> url = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed>(this, "url", "url");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.RssFeed, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final RssFeedMeta slim3_singleton = new RssFeedMeta();

    /**
     * @return the singleton
     */
    public static RssFeedMeta get() {
       return slim3_singleton;
    }

    /** */
    public RssFeedMeta() {
        super("RssFeed", hm.orz.chaos114.gae.rsspocket.model.RssFeed.class);
    }

    @Override
    public hm.orz.chaos114.gae.rsspocket.model.RssFeed entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hm.orz.chaos114.gae.rsspocket.model.RssFeed model = new hm.orz.chaos114.gae.rsspocket.model.RssFeed();
        model.setKey(entity.getKey());
        model.setLatestPublishedDate((java.util.Date) entity.getProperty("latestPublishedDate"));
        model.setSiteUrl((java.lang.String) entity.getProperty("siteUrl"));
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setUrl((java.lang.String) entity.getProperty("url"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.RssFeed) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("latestPublishedDate", m.getLatestPublishedDate());
        entity.setProperty("siteUrl", m.getSiteUrl());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("url", m.getUrl());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.RssFeed) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.RssFeed) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.RssFeed) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.RssFeed) model;
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
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = (hm.orz.chaos114.gae.rsspocket.model.RssFeed) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLatestPublishedDate() != null){
            writer.setNextPropertyName("latestPublishedDate");
            encoder0.encode(writer, m.getLatestPublishedDate());
        }
        if(m.getSiteUrl() != null){
            writer.setNextPropertyName("siteUrl");
            encoder0.encode(writer, m.getSiteUrl());
        }
        if(m.getTitle() != null){
            writer.setNextPropertyName("title");
            encoder0.encode(writer, m.getTitle());
        }
        if(m.getUrl() != null){
            writer.setNextPropertyName("url");
            encoder0.encode(writer, m.getUrl());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected hm.orz.chaos114.gae.rsspocket.model.RssFeed jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.RssFeed m = new hm.orz.chaos114.gae.rsspocket.model.RssFeed();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("latestPublishedDate");
        m.setLatestPublishedDate(decoder0.decode(reader, m.getLatestPublishedDate()));
        reader = rootReader.newObjectReader("siteUrl");
        m.setSiteUrl(decoder0.decode(reader, m.getSiteUrl()));
        reader = rootReader.newObjectReader("title");
        m.setTitle(decoder0.decode(reader, m.getTitle()));
        reader = rootReader.newObjectReader("url");
        m.setUrl(decoder0.decode(reader, m.getUrl()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}