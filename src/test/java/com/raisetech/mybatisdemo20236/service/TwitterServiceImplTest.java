package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.exception.ResourceNotFoundException;
import com.raisetech.mybatisdemo20236.form.TwitterCreateForm;
import com.raisetech.mybatisdemo20236.mapper.TwitterMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TwitterServiceImplTest {


    @InjectMocks
    TwitterServiceImpl twitterServiceImpl;

    @Mock
    TwitterMapper twitterMapper;

    @Test
    public void 存在するIDを指定した時に正常にツイート情報が返されること() {
        doReturn(Optional.of(new Twitter(1, 10, "112"))).when(twitterMapper).findById(1);

        Twitter actual = twitterServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Twitter(1, 10, "112"));
        verify(twitterMapper, times(1)).findById(1);
    }

    @Test
    public void 存在しないIDを指定した時に例外がスローされること() {
        doReturn(Optional.empty()).when(twitterMapper).findById(99);

        assertThatThrownBy(() -> twitterServiceImpl.findById(99))
                .isInstanceOfSatisfying(ResourceNotFoundException.class, e -> {
                    assertThat(e.getMessage()).isEqualTo("This id is not found");
                });
        verify(twitterMapper, times(1)).findById(99);
    }

    @Test
    public void 指定したいいねの数以上ツイート情報が返されること() {
        List<Twitter> twitter = List.of(new Twitter(3, 19, "114"));
        doReturn(twitter).when(twitterMapper).findByLikesGreaterThan(15);

        List<Twitter> actual = twitterServiceImpl.findByLikes(15);
        assertThat(actual).isEqualTo(twitter);
        verify(twitterMapper, times(1)).findByLikesGreaterThan(15);
        verify(twitterMapper, times(0)).findAll();
    }

    @Test
    public void 指定したいいねの数以上のツイート情報が存在しない時に空のリストが返されること() {
        List<Twitter> emptyList = new ArrayList<>();
        doReturn(emptyList).when(twitterMapper).findByLikesGreaterThan(40);
        List<Twitter> actual = twitterServiceImpl.findByLikes(40);
        assertThat(actual).isEqualTo(emptyList);

        verify(twitterMapper, times(1)).findByLikesGreaterThan(40);
        verify(twitterMapper, times(0)).findAll();
    }

    @Test
    public void リクエストでいいねの数の指定がない時に全てのツイート情報が返されること() {
        List<Twitter> twitter = List.of(
                new Twitter(1, 10, "112"),
                new Twitter(2, 15, "113"),
                new Twitter(3, 19, "114"));
        doReturn(twitter).when(twitterMapper).findAll();

        List<Twitter> actual = twitterServiceImpl.findByLikes(null);
        assertThat(actual).isEqualTo(twitter);
        verify(twitterMapper, never()).findByLikesGreaterThan(15);
        verify(twitterMapper, times(1)).findAll();
    }

    @Test
    public void ツイート情報が登録できること() {
        TwitterCreateForm form = new TwitterCreateForm(20, "115");
        Twitter twitter = new Twitter(form.getLikes(), form.getFollowers());
        doNothing().when(twitterMapper).createTwitter(twitter);
        twitterServiceImpl.createTwitter(form);
        verify(twitterMapper, times(1)).createTwitter(twitter);
    }

    @Test
    public void 存在するIDのツイート情報が更新できること() {
        Twitter updateTwitter = new Twitter(1, 8, "110");
        doReturn(Optional.of(new Twitter(1, 10, "112"))).when(twitterMapper).findById(1);

        twitterServiceImpl.updateTwitter(updateTwitter);
        verify(twitterMapper, times(1)).findById(1);
        verify(twitterMapper, times(1)).updateTwitter(updateTwitter);
    }

    @Test
    public void 更新対象のIDが存在しない時に例外がスローされること() {
        Twitter updateTwitter = new Twitter(99, 8, "110");
        doReturn(Optional.empty()).when(twitterMapper).findById(updateTwitter.getId());

        assertThatThrownBy(() -> twitterServiceImpl.updateTwitter(updateTwitter))
                .isInstanceOfSatisfying(ResourceNotFoundException.class, e -> {
                    assertThat(e.getMessage()).isEqualTo("This id is not found");
                });
        verify(twitterMapper, times(1)).findById(updateTwitter.getId());
        verify(twitterMapper, never()).updateTwitter(updateTwitter);
    }

    @Test
    public void 更新リクエストのいいねの数がnullの時に登録済みのいいねの数でツイート情報が更新できること() {
        Twitter updateTwitter = new Twitter(1, null, "112");
        doReturn(Optional.of(new Twitter(1, 10, "112"))).when(twitterMapper).findById(1);

        twitterServiceImpl.updateTwitter(updateTwitter);
        assertThat(updateTwitter.getLikes()).isEqualTo(10);
    }

    @Test
    public void 更新リクエストのフォロワーの数がnullの時に登録済みのフォロワーの数でユーザーが更新できること() {
        Twitter updateTwitter = new Twitter(1, 10, null);
        doReturn(Optional.of(new Twitter(1, 10, "112"))).when(twitterMapper).findById(1);

        twitterServiceImpl.updateTwitter(updateTwitter);
        assertThat(updateTwitter.getFollowers()).isEqualTo("112");
    }

    @Test
    public void 存在するIDのツイート情報が削除できること() {
        doReturn(Optional.of(new Twitter(1, 10, "112"))).when(twitterMapper).findById(1);

        twitterServiceImpl.deleteTwitter(1);
        verify(twitterMapper, times(1)).findById(1);
        verify(twitterMapper, times(1)).deleteTwitter(1);
    }

    @Test
    public void 更新対象のIDが存在しない場合例外がスローされること() {
        doReturn(Optional.empty()).when(twitterMapper).findById(99);

        assertThatThrownBy(() -> twitterServiceImpl.deleteTwitter(99))
                .isInstanceOfSatisfying(ResourceNotFoundException.class, e -> {
                    assertThat(e.getMessage()).isEqualTo("This id is not found");
                });
        verify(twitterMapper, times(1)).findById(99);
        verify(twitterMapper, never()).deleteTwitter(99);

    }
}
