FROM  boxfuse/flyway
ADD /flyway /flyway

CMD ["-X", "-url=jdbc:mariadb://mysql-fifo-service/fifo?allowPublicKeyRetrieval=true&useSSL=false&serverTimeZon=UTC", "-user=root", "-password=root", "-connectRetries=100", "migrate"]
