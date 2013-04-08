package hm.orz.chaos114.gae.rsspocket.dao;

import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.util.List;
import java.util.concurrent.Future;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class RssFeedDao extends DaoBase<RssFeed>{

    @Override
    public Key put(final RssFeed model) {
        setKeyIfNull(model);
        return super.put(model);
    }

    @Override
    public List<Key> put(final List<RssFeed> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public Future<Key> putAsync(final RssFeed model) {
        setKeyIfNull(model);
        return super.putAsync(model);
    }

    @Override
    public Future<List<Key>> putAsync(final List<RssFeed> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    /**
     * Keyが設定されていない場合は、設定されているUserからKeyを生成し、設定する。
     * 
     * @param userInfo ユーザ情報モデル
     */
    private void setKeyIfNull(final RssFeed rssFeed) {
        if (rssFeed.getKey() == null) {
            rssFeed.setKey(createKey(rssFeed.getUrl()));
        }
    }

    private Key createKey(final String url) {
        return Datastore.createKey(RssFeed.class, url);
    }
}
