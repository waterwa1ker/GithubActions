FROM victoriametrics/victoria-metrics
ADD scrape.yml .
RUN nohup ./victoria-metrics-prod -promscrape.config=scrape.yml > log.log &