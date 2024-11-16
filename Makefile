all: check_and_test lint format

check_and_test: FORCE
	mvn test

lint: FORCE
	mvn checkstyle:check

format: FORCE
	mvn formatter:format
FORCE: ;
