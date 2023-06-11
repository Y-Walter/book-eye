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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.interfaceAdapter.api.path.system.SYSTEM_ACCESS_KEY
import java.io.File

/**
 * [SystemController.getVersion]
 */
@SpringBootTest
class SystemController_getVersion(
    private val webApplicationContext: WebApplicationContext
) : BehaviorSpec({
    this as SystemController_getVersion

    Given("正常系") {
        When("有効なSystemアクセスキーの場合") {
            val exp = objectMapper.writeValueAsString(Res(version))
            Then("バージョンを取得できること") {
                getVersion().andExpect(status().isOk())
                    .andExpect(content().json(exp))
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
                getVersion(accessKey).andExpect(status().`is`(401))
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
                getVersion(accessKey).andExpect(status().`is`(401))
                    .andReturn()
            }
        }

        When("アクセスキーを指定しなかった場合") {
            Then("401エラーが返ること") {
                getVersion(accessKey = null).andExpect(status().`is`(401))
                    .andReturn()
            }
        }
    }
}) {
    private val objectMapper = ObjectMapper()

    private val version = File("version.txt").readText().trim()
    fun getVersion(accessKey: String? = SYSTEM_ACCESS_KEY): ResultActions = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .build()
        .perform(
            MockMvcRequestBuilders.get("/api/system/version")
                .apply {
                    if (accessKey != null) header("x-system-key", accessKey)
                }
        )
    private data class Res(
        val version: String
    )
}
