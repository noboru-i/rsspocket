package hm.orz.chaos114.gae.rsspocket.dao;

import hm.orz.chaos114.gae.rsspocket.meta.UserRssMeta;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.users.User;

public class UserRssDao extends DaoBase<UserRss> {

    public List<UserRss> getByUser(final User user) {
        final UserRssMeta e = UserRssMeta.get();
        final List<UserRss> userRssList =
                Datastore.query(e).filter(e.user.equal(user)).asList();

        return userRssList;
    }
}
