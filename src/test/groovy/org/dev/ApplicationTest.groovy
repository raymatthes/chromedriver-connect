package org.dev

import spock.lang.Specification

class ApplicationTest extends Specification {

    def "test determineExecutable"() {
        given:
        Application application = new Application()

        when:
        String actual = application.determineExecutable()

        then:
        actual.startsWith('./bin/')
    }

}
