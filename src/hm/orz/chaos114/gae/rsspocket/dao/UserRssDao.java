package hm.orz.chaos114.gae.rsspocket.dao;

import hm.orz.chaos114.gae.rsspocket.meta.UserRssMeta;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.List;
import java.util.concurrent.Future;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

public class UserRssDao extends DaoBase<UserRss> {

    @Override
    public Key put(final UserRss model) {
        setKeyIfNull(model);
        return super.put(model);
    }

    @Override
    public List<Key> put(final List<UserRss> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public Future<Key> putAsync(final UserRss model) {
        setKeyIfNull(model);
        return super.putAsync(model);
    }

    @Override
    public Future<List<Key>> putAsync(final List<UserRss> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    /**
     * 指定されたユーザのRSS一覧を取得する。
     * 
     * @param user 取得したいユーザ情報
     * @return RSS一覧情報
     */
    public List<UserRss> getByUser(final User user) {
        final UserRssMeta e = UserRssMeta.get();
        final List<UserRss> userRssList =
                Datastore.query(e).filter(e.user.equal(user)).asList();

        return userRssList;
    }

    /**
     * Keyが設定されていない場合は、設定されているUserからKeyを生成し、設定する。
     * 
     * @param userInfo ユーザ情報モデル
     */
    private void setKeyIfNull(final UserRss userRss) {
        if (userRss.getKey() == null) {
            userRss.setKey(createKey(userRss));
        }
    }

    private Key createKey(final UserRss userRss) {
        final String email = userRss.getUser().getEmail();
        final String url = userRss.getRssFeed().getModel().getUrl();
        return Datastore.createKey(UserRss.class, email + url);
    }
}
