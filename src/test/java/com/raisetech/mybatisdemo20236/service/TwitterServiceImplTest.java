package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.mapper.TwitterMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
}
