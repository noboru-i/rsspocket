package hm.orz.chaos114.gae.rsspocket.dao;

import hm.orz.chaos114.gae.rsspocket.model.UserInfo;

import java.util.List;
import java.util.concurrent.Future;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

public class UserInfoDao extends DaoBase<UserInfo>{

    @Override
    public Key put(final UserInfo model) {
        setKeyIfNull(model);
        return super.put(model);
    }

    @Override
    public List<Key> put(final List<UserInfo> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public Future<Key> putAsync(final UserInfo model) {
        setKeyIfNull(model);
        return super.putAsync(model);
    }

    @Override
    public Future<List<Key>> putAsync(final List<UserInfo> models) {
        // 利用しないため未実装
        throw new UnsupportedOperationException("未実装");
    }

    /**
     * Userが一致するUserInfoオブジェクトを取得する。
     * 
     * @param user ユーザ情報
     * @return UserInfoオブジェクト
     */
    public UserInfo getByUser(final User user) {
        final Key key = createKey(user);
        try {
            return get(key);
        } catch (final EntityNotFoundRuntimeException e) {
            return null;
        }
    }

    /**
     * 指定されたUserに
     * @param user
     * @param pocketAccessToken
     * @return
     */
    public Future<Key> updatePocketAccessTokenAsync(final User user, final String pocketAccessToken) {
        final Key key = createKey(user);
        final UserInfo userInfo = get(key);
        userInfo.setPocketAccessToken(pocketAccessToken);

        return putAsync(userInfo);
    }

    /**
     * Keyが設定されていない場合は、設定されているUserからKeyを生成し、設定する。
     * 
     * @param userInfo ユーザ情報モデル
     */
    private void setKeyIfNull(final UserInfo userInfo) {
        if (userInfo.getKey() == null) {
            userInfo.setKey(createKey(userInfo.getUser()));
        }
    }

    private Key createKey(final User user) {
        return Datastore.createKey(UserInfo.class, user.getEmail());
    }
}
