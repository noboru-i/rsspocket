package hm.orz.chaos114.gae.rsspocket.service.rss;

import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;
import net.arnx.jsonic.JSON;

import com.google.appengine.api.users.User;


public class TagService {

    /**
     * 指定されたユーザのRSS情報を上書きする。
     * 
     * @param user 更新対象のユーザ情報
     * @param jsonStr 更新情報
     */
    public void edit(final User user, final String jsonStr) {
        final RssTagParameter[] parameters = JSON.decode(jsonStr, RssTagParameter[].class);

        final RssFeedDao rssFeedDao = new RssFeedDao();
        final UserRssDao userRssDao = new UserRssDao();

        for (final RssTagParameter parameter : parameters) {
            final RssFeed rssFeed = new RssFeed();
            rssFeed.setUrl(parameter.getUrl());
            rssFeedDao.put(rssFeed);

            final UserRss userRss = new UserRss();
            userRss.setUser(user);
            userRss.getRssFeed().setModel(rssFeed);
            userRss.setTags(parameter.getTags());
            userRssDao.put(userRss);
        }
    }

    /**
     * 指定されたユーザのRSS情報を削除する。
     * 
     * @param user 削除対象のユーザ情報
     * @param jsonStr 削除用のキーが設定された情報
     */
    public void delete(final User user, final String jsonStr) {
        final RssTagParameter[] parameters = JSON.decode(jsonStr, RssTagParameter[].class);

        final UserRssDao userRssDao = new UserRssDao();

        for (final RssTagParameter parameter : parameters) {
            final UserRss model = userRssDao.get(user, parameter.getUrl());
            userRssDao.deleteAsync(model);
        }
    }
}
