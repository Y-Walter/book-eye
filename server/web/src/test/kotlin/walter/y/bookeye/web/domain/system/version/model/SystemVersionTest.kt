package walter.y.bookeye.web.domain.system.version.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

/**
 * [SystemVersion] 初期状態
 */
class SystemVersionTest : BehaviorSpec({
    Given("NULL") {
        When("NULLの場合") {
            val version = null
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
    }

    Given("Blank") {
        When("空文字の場合") {
            val version = ""
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
        When("半角スペースのみの場合") {
            val version = " "
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
        When("全角スペースのみの場合") {
            val version = "　"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
    }

    Given("トリミング") {
        When("前後に半角スペースが含まれる場合") {
            val version = " 1.0.0 "
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("前後に全角スペースが含まれる場合") {
            val version = "　1.0.1　"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
    }

    Given("バージョン表記(X.Y.Z)") {
        When("0.0.0の場合") {
            val version = "0.0.0"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("1.0.0の場合") {
            val version = "1.0.0"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("1.2.3の場合") {
            val version = "1.2.3"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("10.20.30の場合") {
            val version = "10.20.30"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("100.200.300の場合") {
            val version = "100.200.300"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("100.200.300の場合") {
            val version = "100.200.300"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("1.2.3-alphaの場合") {
            val version = "1.2.3-alpha"
            Then("インスタンスの生成に成功すること") {
                shouldNotThrowAny {
                    SystemVersion(version)
                }
            }
        }
        When("1.2.3-の場合") {
            val version = "1.2.3-"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }

        When("000.001.0002の場合") {
            val version = "000.001.0002"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
        When("1-2-3の場合") {
            val version = "1-2-3"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
        When("全角１.２.３の場合") {
            val version = "１.２.３"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemVersion(version)
                }
            }
        }
    }
})
