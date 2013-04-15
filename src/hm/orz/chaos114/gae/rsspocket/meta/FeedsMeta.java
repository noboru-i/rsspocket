package hm.orz.chaos114.gae.rsspocket.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-04-16 00:42:45")
/** */
public final class FeedsMeta extends org.slim3.datastore.ModelMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds> description = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds>(this, "description", "description");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds> link = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds>(this, "link", "link");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, java.util.Date> publishedDate = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, java.util.Date>(this, "publishedDate", "publishedDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, org.slim3.datastore.ModelRef<hm.orz.chaos114.gae.rsspocket.model.RssFeed>, hm.orz.chaos114.gae.rsspocket.model.RssFeed> rssFeed = new org.slim3.datastore.ModelRefAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, org.slim3.datastore.ModelRef<hm.orz.chaos114.gae.rsspocket.model.RssFeed>, hm.orz.chaos114.gae.rsspocket.model.RssFeed>(this, "rssFeed", "rssFeed", org.slim3.datastore.ModelRef.class, hm.orz.chaos114.gae.rsspocket.model.RssFeed.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds> title = new org.slim3.datastore.StringAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds>(this, "title", "title");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<hm.orz.chaos114.gae.rsspocket.model.Feeds, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final FeedsMeta slim3_singleton = new FeedsMeta();

    /**
     * @return the singleton
     */
    public static FeedsMeta get() {
       return slim3_singleton;
    }

    /** */
    public FeedsMeta() {
        super("Feeds", hm.orz.chaos114.gae.rsspocket.model.Feeds.class);
    }

    @Override
    public hm.orz.chaos114.gae.rsspocket.model.Feeds entityToModel(com.google.appengine.api.datastore.Entity entity) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds model = new hm.orz.chaos114.gae.rsspocket.model.Feeds();
        model.setDescription((java.lang.String) entity.getProperty("description"));
        model.setKey(entity.getKey());
        model.setLink((java.lang.String) entity.getProperty("link"));
        model.setPublishedDate((java.util.Date) entity.getProperty("publishedDate"));
        if (model.getRssFeed() == null) {
            throw new NullPointerException("The property(rssFeed) is null.");
        }
        model.getRssFeed().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("rssFeed"));
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("description", m.getDescription());
        entity.setProperty("link", m.getLink());
        entity.setProperty("publishedDate", m.getPublishedDate());
        if (m.getRssFeed() == null) {
            throw new NullPointerException("The property(rssFeed) must not be null.");
        }
        entity.setProperty("rssFeed", m.getRssFeed().getKey());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
        if (m.getRssFeed() == null) {
            throw new NullPointerException("The property(rssFeed) must not be null.");
        }
        m.getRssFeed().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
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
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = (hm.orz.chaos114.gae.rsspocket.model.Feeds) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getDescription() != null){
            writer.setNextPropertyName("description");
            encoder0.encode(writer, m.getDescription());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLink() != null){
            writer.setNextPropertyName("link");
            encoder0.encode(writer, m.getLink());
        }
        if(m.getPublishedDate() != null){
            writer.setNextPropertyName("publishedDate");
            encoder0.encode(writer, m.getPublishedDate());
        }
        if(m.getRssFeed() != null && m.getRssFeed().getKey() != null){
            writer.setNextPropertyName("rssFeed");
            encoder0.encode(writer, m.getRssFeed(), maxDepth, currentDepth);
        }
        if(m.getTitle() != null){
            writer.setNextPropertyName("title");
            encoder0.encode(writer, m.getTitle());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected hm.orz.chaos114.gae.rsspocket.model.Feeds jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        hm.orz.chaos114.gae.rsspocket.model.Feeds m = new hm.orz.chaos114.gae.rsspocket.model.Feeds();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("description");
        m.setDescription(decoder0.decode(reader, m.getDescription()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("link");
        m.setLink(decoder0.decode(reader, m.getLink()));
        reader = rootReader.newObjectReader("publishedDate");
        m.setPublishedDate(decoder0.decode(reader, m.getPublishedDate()));
        reader = rootReader.newObjectReader("rssFeed");
        decoder0.decode(reader, m.getRssFeed(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("title");
        m.setTitle(decoder0.decode(reader, m.getTitle()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}