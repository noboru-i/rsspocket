package hm.orz.chaos114.gae.rsspocket.dao.user;

import hm.orz.chaos114.gae.rsspocket.meta.user.TokensMeta;
import hm.orz.chaos114.gae.rsspocket.model.user.Tokens;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

public class TokensDao extends DaoBase<Tokens>{

    public Tokens getByUserId(final String userId) {
        final TokensMeta meta = TokensMeta.get();
        final Tokens token =
                Datastore.query(meta).filter(meta.userId.equal(userId)).asSingle();
        return token;
    }

}
