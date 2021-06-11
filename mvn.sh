#!/usr/bin/env bash

function mvn() {
    if [[ $# == 0 || $1 == "help" ]]; then
        echo "Usage
  mvn run     <class name> [<arg(s)>]
  mvn testrun <class name>
  mvn ..."
        return 0
    elif [[ $1 == "run" ]]; then
        if [[ $# == 1 ]]; then
            echo "mvn run <class name> [<arg(s)>]"
            return 1
        fi
        local class_name="$2"
        shift 2
        command mvn compile exec:java \
                            --define exec.mainClass="${class_name}" \
                            --define exec.args="$*"
    elif [[ $1 == "runtest" || $1 == "testrun" ]]; then
        if [[ $# != 2 ]]; then
            echo "mvn testrun <class name>"
            return 1
        fi
        local class_name="$2"
        command mvn test-compile exec:java --define exec.classpathScope='test' --define exec.mainClass="${class_name}"
    else
        command mvn "$@"
    fi
}

mvn "$@"


