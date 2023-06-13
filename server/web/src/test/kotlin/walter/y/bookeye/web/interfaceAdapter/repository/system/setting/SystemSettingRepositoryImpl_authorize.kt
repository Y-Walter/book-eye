@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.repository.system.setting

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.domain.system.setting.model.SystemSettingEntity
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client.SystemSettingClient
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client.SystemSettingClientException

/**
 * [SystemSettingRepositoryImpl.authorize]
 */
class SystemSettingRepositoryImpl_authorize : BehaviorSpec({
    Given("正常系") {
        val mockSystemSettingClient = mockk<SystemSettingClient>()
        val sut = SystemSettingRepositoryImpl(mockSystemSettingClient)
        When("有効なSystemAccessKeyを使用した場合") {
            every {
                mockSystemSettingClient.authorize(any())
            } returns Unit
            val systemAccessKey = SystemAccessKey("unit-test-system-access-key")
            val systemSettingEntity = SystemSettingEntity(systemAccessKey = systemAccessKey)
            Then("エラーが発生しないこと") {
                shouldNotThrowAny {
                    sut.authorize(systemSettingEntity)
                }
            }
            Then("SystemSettingClient.authorize が呼び出されていること") {
                verify(exactly = 1) {
                    mockSystemSettingClient.authorize(accessKey = systemAccessKey)
                }
            }
        }
    }

    Given("異常系") {
        val mockSystemSettingClient = mockk<SystemSettingClient>()
        val sut = SystemSettingRepositoryImpl(mockSystemSettingClient)
        When("SystemSettingClientException.Unauthorized が発生した場合") {
            val ex = SystemSettingClientException.Unauthorized(message = "unauthorized")
            every {
                mockSystemSettingClient.authorize(any())
            } throws ex
            val systemSettingEntity = SystemSettingEntity(
                systemAccessKey = SystemAccessKey("unit-test-system-access-key")
            )
            Then("SystemSettingRepositoryException.Unauthorized を返すこと") {
                val act = shouldThrow<SystemSettingRepositoryException.Unauthorized> {
                    sut.authorize(systemSettingEntity)
                }
                act.cause shouldBe ex
            }
        }
        When("SystemSettingClientException.NotFound が発生した場合") {
            val ex = SystemSettingClientException.NotFound(message = "not found")
            every {
                mockSystemSettingClient.authorize(any())
            } throws ex
            val systemSettingEntity = SystemSettingEntity(
                systemAccessKey = SystemAccessKey("unit-test-system-access-key")
            )
            Then("SystemSettingRepositoryException.NotFound を返すこと") {
                val act = shouldThrow<SystemSettingRepositoryException.NotFound> {
                    sut.authorize(systemSettingEntity)
                }
                act.cause shouldBe ex
            }
        }
        When("Exception が発生した場合") {
            val ex = Exception("exception")
            every {
                mockSystemSettingClient.authorize(any())
            } throws ex
            val systemSettingEntity = SystemSettingEntity(
                systemAccessKey = SystemAccessKey("unit-test-system-access-key")
            )
            Then("Exception は、そのまま返ること") {
                val act = shouldThrow<Exception> {
                    sut.authorize(systemSettingEntity)
                }
                act shouldBe ex
            }
        }
    }
})
