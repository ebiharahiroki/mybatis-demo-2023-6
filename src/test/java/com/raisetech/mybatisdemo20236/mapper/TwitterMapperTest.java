package com.raisetech.mybatisdemo20236.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.mybatisdemo20236.entity.Twitter;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TwitterMapperTest {

    @Autowired
    TwitterMapper twitterMapper;

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 全てのユーザーが取得できること() {
        assertThat(twitterMapper.findAll())
                .hasSize(3)
                .contains(
                        new Twitter(1, 10, "112"),
                        new Twitter(2, 15, "113"),
                        new Twitter(3, 19, "114")
                );
    }

    @Test
    @DataSet(value = "datasets/empty.yml")
    @Transactional
    void DBが空の時に空のリストが返されること() {
        assertThat(twitterMapper.findAll()).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したIDのツイート情報が取得できること() {
        assertThat(twitterMapper.findById(1))
                .contains(new Twitter(1, 10, "112"));
    }

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したIDのツイートが存在しない時に空のOptionalが返されること() {
        assertThat(twitterMapper.findById(4)).isEmpty();
    }
    
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したいいねの数より上のフォロワー情報が取得できること() {
        assertThat(twitterMapper.findByLikesGreaterThan(14))
                .hasSize(2)
                .contains(
                        new Twitter(2, 15, "113"),
                        new Twitter(3, 19, "114")
                );
    }

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したいいねの数より上のフォロワー情報が存在しない時に空のリストが返されること() {
        assertThat(twitterMapper.findByLikesGreaterThan(35)).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/insert_twitter.yml", ignoreCols = "id")
    @Transactional
    void ツイートが登録でき既存のIDより大きい数字のIDが採番されること() {
        Twitter twitter = new Twitter(20, "115");
        twitterMapper.createTwitter(twitter);
        assertThat(twitter.getId()).isGreaterThan(3);
    }

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/update_twitter.yml")
    @Transactional
    void 指定したIDのツイート情報が更新できること() {
        twitterMapper.updateTwitter(new Twitter(3, 20, "115"));
    }

    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/delete_twitter.yml")
    @Transactional
    void 指定したIDのツイート情報を削除できること() {
        twitterMapper.deleteTwitter(3);
    }
}
