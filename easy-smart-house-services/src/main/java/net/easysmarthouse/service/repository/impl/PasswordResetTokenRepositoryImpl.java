package net.easysmarthouse.service.repository.impl;

import net.easysmarthouse.service.repository.PasswordResetTokenRepository;
import net.easysmarthouse.shared.domain.user.PasswordResetToken;
import net.easysmarthouse.shared.domain.user.VerificationToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Repository
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {

    private Logger logger = Logger.getLogger(PasswordResetTokenRepositoryImpl.class);

    private class PasswordResetTokenMapper implements RowMapper<PasswordResetToken> {
        @Override
        public PasswordResetToken mapRow(ResultSet rs, int rowNum) throws SQLException {
            PasswordResetToken token = new PasswordResetToken();
            token.setId(rs.getLong("ID"));
            token.setToken(rs.getString("TOKEN"));
            token.setExpiryDate(rs.getDate("EXPIRY_DATE"));
            token.setUserId(rs.getLong("USER_ID"));
            return token;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PasswordResetToken save(PasswordResetToken token) {
        long tokenId = jdbcTemplate.update("INSERT INTO PASSWORD_RESET_TOKEN (ID, TOKEN, EXPIRY_DATE, USER_ID) VALUES(?, ?, ?, ?)",
                new Object[]{token.getId(),
                        token.getToken(),
                        token.getExpiryDate(),
                        token.getId()}
        );
        token.setId(tokenId);
        return token;
    }

    @Override
    @Transactional(readOnly = true)
    public PasswordResetToken getToken(String token) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PASSWORD_RESET_TOKEN WHERE TOKEN=?",
                    new Object[]{token},
                    new PasswordResetTokenMapper()
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PasswordResetToken update(PasswordResetToken token) {
        try {
            jdbcTemplate.update("UPDATE PASSWORD_RESET_TOKEN SET (TOKEN, EXPIRY_DATE) = (?, ?) WHERE ID = ? ",
                    new Object[]{token.getToken(), token.getExpiryDate()}
            );
            return token;
        } catch (Exception ex) {
            logger.error("Couldn't update token", ex);
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteExpiredSince(Date date) {
        jdbcTemplate.update("DELETE FROM PASSWORD_RESET_TOKEN WHERE EXPIRY_DATE <= ?",
                new Object[]{date});
    }
}
