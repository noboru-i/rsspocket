package hm.orz.chaos114.gae.rsspocket.dao;

import hm.orz.chaos114.gae.rsspocket.meta.FeedsMeta;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.util.List;
import java.util.concurrent.Future;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

import com.google.appengine.api.datastore.Key;

public class FeedsDao extends DaoBase<Feeds> {

    @Override
    public Key put(final Feeds model) {
        setKeyIfNull(model);
        return super.put(model);
    }

    @Override
    public List<Key> put(final List<Feeds> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public Future<Key> putAsync(final Feeds model) {
        setKeyIfNull(model);
        return super.putAsync(model);
    }

    @Override
    public Future<List<Key>> putAsync(final List<Feeds> models) {
        for (final Feeds feed : models) {
            setKeyIfNull(feed);
        }
        return super.putAsync(models);
    }

    public List<Feeds> getList(final RssFeed rssFeed, final int offset,
            final int limit) {
        final FeedsMeta e = FeedsMeta.get();
        return Datastore.query(e).filter(e.rssFeed.equal(rssFeed.getKey()))
                .sort(e.publishedDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }

    public Feeds getByLink(final String link) {
        final Key key = createKey(link);
        try {
            return get(key);
        } catch (final EntityNotFoundRuntimeException e) {
            return null;
        }
    }

    private void setKeyIfNull(final Feeds feeds) {
        if (feeds.getKey() == null) {
            feeds.setKey(createKey(feeds.getLink()));
        }
    }

    private Key createKey(final String link) {
        return Datastore.createKey(Feeds.class, link);
    }
}
