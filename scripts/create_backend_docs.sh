#!/bin/bash

JAVADOC_GENERATED_DIR="$FITNESS_FORGE_ROOT_DIR/backend/target/site/apidocs/"
DOCS_ROOT_DIR="$FITNESS_FORGE_ROOT_DIR/javadocs"

cd "$FITNESS_FORGE_ROOT_DIR/backend"
mvn clean
mvn javadoc:javadoc

cd

rm -rf "$DOCS_ROOT_DIR"
mv "$JAVADOC_GENERATED_DIR" "$DOCS_ROOT_DIR"
