package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.mybatisdemo20236.MybatisDemo20236Application;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = MybatisDemo20236Application.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class TwitterRestApiIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    // GETメソッドで存在するIDを指定した時に、指定したIDのツイート情報が取得できステータスコード200が返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したIDのツイート情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tweets/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    {
                    "id": 1,
                    "likes": 10,
                    "followers": "112"
                    }
                """, response, JSONCompareMode.STRICT);
    }

    // GETメソッドで存在しないIDを指定した時に、例外がスローされステータスコード404とエラーメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したIDのツイート情報が存在しない時に例外がスローされること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tweets/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                            {
                            "timestamp": "2023-06-05T18:13:22.414491+09:00[Asia/Tokyo]",
                            "status": "404",
                            "error": "Not Found",
                            "message": "This id is not found",
                            "path": "/tweets/4"
                            }
                        """,
                response,
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("timestamp", ((o1, o2) -> true))));
    }

    // GETメソッドでクエリパラメータでフォロワー数を指定しない時に、ユーザーが全件取得できステータスコード200が返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void フォロワー数を指定しない時にユーザーが全件取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tweets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    [
                    {
                    "id": 1,
                    "likes": 10,
                    "followers": "112"
                    },
                    {
                    "id": 2,
                    "likes": 15,
                    "followers": "113"
                    },
                    {
                    "id": 3,
                    "likes": 19,
                    "followers": "114"
                    }
                    ]
                """, response, JSONCompareMode.STRICT);
    }

    // GETメソッドでDBが空の時に、空のListが返されステータスコード200が返されること
    @Test
    @DataSet(value = "datasets/empty.yml")
    @Transactional
    void DBが空の時に空のListが返されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tweets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                []
                """, response, JSONCompareMode.STRICT);
    }

    // GETメソッドでクエリパラメータでいいねの数を指定した時に、指定したいいねの数より上のツイート情報が取得できステータスコード200が返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したいいねの数より上のツイート情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tweets?likes=15"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    [
                    {
                    "id": 3,
                    "likes": 19,
                    "followers": "114"
                    }
                    ]
                """, response, JSONCompareMode.STRICT);
    }

    // GETメソッドでクエリパラメータで指定したいいねの数より上のツイート情報が存在しない時に、空のListとステータスコード200が返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 指定したいいねの数より上のツイート情報が存在しない時に空のListが返されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/tweets?likes=35"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                    []
                """, response, JSONCompareMode.STRICT);
    }

    // POSTメソッドで正しくリクエストした時に、ツイート情報が登録できステータスコード201とメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/insert_twitter.yml", ignoreCols = "id")
    @Transactional
    void ツイート情報が登録できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/tweets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": 20,
                                "followers": "115"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                "message": "tweets information successfully created"
                }
                """, response, JSONCompareMode.STRICT);
    }

    // POSTメソッドでリクエストのlikesがnullの時に、ステータスコード400とエラーメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 登録時のリクエストのlikesがnullの時にエラーメッセージが返されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/tweets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": null,
                                "followers": "115"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    // POSTメソッドでリクエストのfollowersがnullの時に、ステータスコード400とエラーメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 登録時のリクエストのfollowersがnullの時にエラーメッセージが返されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/tweets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": 20,
                                "followers": null
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    // PATCHメソッドで存在するIDを指定した時に、ツイート情報が更新できステータスコード201とメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/update_twitter.yml")
    @Transactional
    void ツイート情報が更新できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/tweets/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": 20,
                                "followers": "115"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                "message": "tweets information successfully updated"
                }
                """, response, JSONCompareMode.STRICT);
    }

    // PATCHメソッドで存在するIDを指定しリクエストのlikesがnullの時に、followersのみ更新されステータスコード201とメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/update_twitter_followers.yml")
    @Transactional
    void 更新リクエストでlikesがnullの時followersのみ更新されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/tweets/3")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": null,
                                "followers": "116"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                "message": "tweets information successfully updated"
                }
                """, response, JSONCompareMode.STRICT);
    }

    // PATCHメソッドで存在するIDを指定しリクエストのfollowersがnullの時に、likesのみ更新されステータスコード201とメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/update_twitter_likes.yml")
    @Transactional
    void 更新リクエストでfollowersがnullの時likesのみ更新されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/tweets/3")

                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": 25,
                                "followers": null
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                "message": "tweets information successfully updated"
                }
                """, response, JSONCompareMode.STRICT);
    }

    // PATCHメソッドで存在しないIDを指定した時に、例外がスローされステータスコード404とエラーメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 更新リクエストで指定したIDのツイート情報が存在しない時に例外がスローされること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.patch("/tweets/4")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "likes": 20,
                                "followers": "115"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                            {
                            "timestamp": "2023-06-05T18:13:22.414491+09:00[Asia/Tokyo]",
                            "status": "404",
                            "error": "Not Found",
                            "message": "This id is not found",
                            "path": "/tweets/4"
                            }
                        """,
                response,
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("timestamp", ((o1, o2) -> true))));
    }

    // DELETEメソッドで存在するIDを指定した時に、ツイート情報が削除できステータスコード200とメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @ExpectedDataSet(value = "datasets/delete_twitter.yml")
    @Transactional
    void ツイート情報が削除できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/tweets/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                {
                "message": "tweets information successfully deleted"
                }
                """, response, JSONCompareMode.STRICT);
    }

    // DELETEメソッドで存在しないIDを指定した時に、例外がスローされステータスコード404とエラーメッセージが返されること
    @Test
    @DataSet(value = "datasets/twitter.yml")
    @Transactional
    void 削除リクエストで指定したIDのツイート情報が存在しない時に例外がスローされること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/tweets/4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals("""
                            {
                            "timestamp": "2023-06-05T18:13:22.414491+09:00[Asia/Tokyo]",
                            "status": "404",
                            "error": "Not Found",
                            "message": "This id is not found",
                            "path": "/tweets/4"
                            }
                        """,
                response,
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("timestamp", ((o1, o2) -> true))));
    }
}
