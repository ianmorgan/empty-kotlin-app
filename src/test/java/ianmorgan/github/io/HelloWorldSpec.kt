package ianmorgan.github.io

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@RunWith(JUnitPlatform::class)
object HelloWorldSpec : Spek({

    describe ("Printing a greeting") {

        // make sure System.our is preserved
        lateinit var systemOut: PrintStream
        beforeEachTest {
            systemOut = System.out
        }

        afterEachTest {
            System.setOut(systemOut)
        }

        it ("should include name if provided") {
            // setup
            val out = ByteArrayOutputStream()
            System.setOut(PrintStream(out))

            // execute
            main(arrayOf("Ian"))

            // assert
            assert.that(out.toString(), equalTo("Hello, Ian\r\n"))
        }

        it ("should just Hello World if no name") {
            // setup
            val out = ByteArrayOutputStream()
            System.setOut(PrintStream(out))

            // execute
            main(arrayOf())

            // assert
            assert.that(out.toString(), equalTo("Hello World\r\n"))
        }
    }
})