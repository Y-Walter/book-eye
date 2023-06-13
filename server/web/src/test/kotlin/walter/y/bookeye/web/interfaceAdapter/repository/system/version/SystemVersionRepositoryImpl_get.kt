@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.repository.system.version

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.system.version.model.SystemVersion
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepositoryException
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client.SystemVersionClient
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client.SystemVersionClientException

/**
 * [SystemVersionRepositoryImpl.get]
 */
class SystemVersionRepositoryImpl_get : BehaviorSpec({
    Given("正常系") {
        val mockSystemVersionClient = mockk<SystemVersionClient>()
        val sut = SystemVersionRepositoryImpl(mockSystemVersionClient)

        When("メソッドを実行した場合") {
            val version = "1.2.3"
            every {
                mockSystemVersionClient.getVersion()
            } returns SystemVersion(version)
            val act = sut.get()

            Then("バージョンを取得できること") {
                act.version.value shouldBe version

                verify(exactly = 1) {
                    mockSystemVersionClient.getVersion()
                }
            }
        }
    }

    Given("異常系") {
        val mockSystemVersionClient = mockk<SystemVersionClient>()
        val sut = SystemVersionRepositoryImpl(mockSystemVersionClient)
        When("SystemVersionClientException.NotFound が発生した場合") {
            val ex = SystemVersionClientException.NotFound(message = "not found")
            every {
                mockSystemVersionClient.getVersion()
            } throws ex
            Then("SystemVersionRepositoryException.NotFound を返すこと") {
                shouldThrow<SystemVersionRepositoryException.NotFound> {
                    sut.get()
                }
//                act.cause shouldBe ex
            }
        }
        When("Exception が発生した場合") {
            val ex = Exception("exception")
            every {
                mockSystemVersionClient.getVersion()
            } throws ex
            Then("Exception のまま返すこと") {
                val act = shouldThrow<Exception> {
                    sut.get()
                }
                act shouldBe ex
            }
        }
    }
})
