package hm.orz.chaos114.gae.rsspocket.dao;

import hm.orz.chaos114.gae.rsspocket.meta.FeedsMeta;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

public class FeedsDao extends DaoBase<Feeds> {

    public List<Feeds> getList(final RssFeed rssFeed, final int offset,
            final int limit) {
        final FeedsMeta e = FeedsMeta.get();
        return Datastore.query(e).filter(e.rssFeed.equal(rssFeed.getKey()))
                .sort(e.publishedDate.desc)
                .offset(offset)
                .limit(limit)
                .asList();
    }
}
