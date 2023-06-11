@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.interfaceAdapter.api.path.system.SYSTEM_ACCESS_KEY

/**
 * [SystemController.checkHealth]
 */
@SpringBootTest
class SystemController_checkHealth(
    private val webApplicationContext: WebApplicationContext
) : BehaviorSpec({
    this as SystemController_checkHealth

    Given("正常系") {
        When("有効なSystemアクセスキーの場合") {
            val exp = objectMapper.writeValueAsString(Res(message = "SUCCESSFUL"))
            Then("メッセージを取得できること") {
                checkHealth().andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json(exp))
                    .andReturn()
            }
        }
    }

    Given("異常系") {
        When("バリデーションを満たす異なるSystemアクセスキーを指定した場合") {
            val accessKey = "invalid-integration-test-system-access-key"
            Then("SystemAccessKeyのバリデーションを満たすこと") {
                assertDoesNotThrow {
                    SystemAccessKey(accessKey)
                }
            }
            Then("デフォルトのSystemアクセスキーと異なること") {
                accessKey shouldNotBe SYSTEM_ACCESS_KEY
            }
            Then("401エラーが返ること") {
                checkHealth(accessKey).andExpect(MockMvcResultMatchers.status().`is`(401))
                    .andReturn()
            }
        }

        When("バリデーションを満たさない異なるSystemアクセスキーを指定した場合") {
            val accessKey = "invalid-key"
            Then("SystemAccessKeyのバリデーションを満たさないこと") {
                assertThrows<IllegalArgumentException> {
                    SystemAccessKey(accessKey)
                }
            }
            Then("デフォルトのSystemアクセスキーと異なること") {
                accessKey shouldNotBe SYSTEM_ACCESS_KEY
            }

            Then("401エラーが返ること") {
                checkHealth(accessKey).andExpect(MockMvcResultMatchers.status().`is`(401))
                    .andReturn()
            }
        }

        When("アクセスキーを指定しなかった場合") {
            Then("401エラーが返ること") {
                checkHealth(accessKey = null).andExpect(MockMvcResultMatchers.status().`is`(401))
                    .andReturn()
            }
        }
    }
}) {
    private val objectMapper = ObjectMapper()
    fun checkHealth(accessKey: String? = SYSTEM_ACCESS_KEY): ResultActions = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .build()
        .perform(
            MockMvcRequestBuilders.get("/api/system/health")
                .apply {
                    if (accessKey != null) header("x-system-key", accessKey)
                }
        )
    private data class Res(
        val message: String
    )
}
