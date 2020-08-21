## Criação de KeyStore e TrustStore

### SERVER:

Os seguintes comandos para:  
1. Criar chave privada e certificado do CA Raiz  
2. Criar chave privada e certificado do Servidor  
3. Assinar o certificado do Servidor com o CA Raiz  
4. Criar o KeyStore PKCS12 com o certificado assinado do Servidor

```bash
$1> openssl req -x509 -sha256 -days 365 -newkey rsa:4096 -subj "/C=BR/ST=MG/L=Belo Horizonte/O=OtoJunior Company Ltda/CN=OTOJUNIOR_COMPANY_CA" -keyout rootca.key -out rootca.crt
$2> openssl req -new -newkey rsa:4096 -subj "/C=BR/ST=MG/L=Belo Horizonte/O=OtoJunior Provedor de Servicos Ltda/CN=OTOJUNIOR_SERVICE_PROVIDER" -keyout server.key -out server.csr
$3> openssl x509 -req -CAcreateserial -CAkey rootca.key -CA rootca.crt -days 365 -in server.csr -out server.crt
$4> openssl pkcs12 -export -name "otojunior-server" -inkey server.key -in server.crt -out server.keystore.p12
```

Criar TrustStore:
1. Criar o TrustStore PKCS12 com o certificado do CA Raiz somente

```bash
$1> keytool -import -trustcacerts -noprompt -alias ca -file rootca.crt -keystore server.truststore.jks
```

### CLIENT

Os seguintes comandos para:
1. Criar chave privada e certificado do Cliente
2. Assinar o certificado do Servidor com o CA Raiz
3. Criar o KeyStore PKCS12 com o certificado assinado do Servidor

```bash
$1> openssl req -new -newkey rsa:4096 -subj "/C=BR/ST=MG/L=Belo Horizonte/CN=otojunior" -nodes -keyout otojunior.key -out otojunior.csr
$2> openssl x509 -req -CAcreateserial -CAkey rootca.key -CA rootca.crt -days 365 -in otojunior.csr -out otojunior.crt
$3> openssl pkcs12 -export -name "otojunior" -inkey otojunior.key -in otojunior.crt -out otojunior.p12
```

### Comandos para verificar os certificados:

```bash
$> openssl x509 -in rootca.crt -text -noout
$> keytool -printcert -v -file rootca.crt
```

### Comandos para verificar a keystore:

```bash
$> keytool -list -v -keystore server.p12
```