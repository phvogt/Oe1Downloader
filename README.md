# OE1 Downloader

[![Build Status](https://travis-ci.org/phvogt/Oe1Downloader.svg?branch=master)](https://travis-ci.org/phvogt/Oe1Downloader)
[![Coverage Status](https://coveralls.io/repos/phvogt/Oe1Downloader/badge.svg?branch=master&service=github)](https://coveralls.io/github/phvogt/Oe1Downloader?branch=master)
[![Coverity Scan Build Status](https://img.shields.io/coverity/scan/6414.svg)](https://scan.coverity.com/projects/phvogt-oe1downloader)

This project provides an application to download shows from the [7 day archive](https://oe1.orf.at/player/) from the austrian radio station [Ö1](https://oe1.orf.at).

The radio station provides an [android app](https://play.google.com/store/apps/details?id=com.nousguide.oe1&hl=en) as well.

# Features

* Download of the MP3s of the shows that are publicly provided by the radio station.
* Provide rules which shows to download.

# Usage

To use this application follow these steps:

1. Build the application from the source:
<pre>
mvn clean package
</pre>
1. go to / create the target directory
1. create a sub directory named `conf` containing
    * the file [conf/config.properties](conf/config.properties)
    * and the file [conf/log4j.properties](conf/log4j.properties)
1. configure the rules in `conf/config.properties` and the other settings
1. copy the file from the source folder `target/Oe1Downloader-1.0.0-jar-with-dependencies.jar` to the target 
directory
1. run the application in the target directory with
<pre>
java -jar Oe1Downloader-1.0.0-jar-with-dependencies.jar
</pre>
