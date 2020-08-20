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
$3> openssl x509 -req -sha256 -CAcreateserial -CAkey rootca.key -CA rootca.crt -days 365 -in server.csr -out server.crt
$4> openssl pkcs12 -export -inkey server.key -in server.crt -out server.p12
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
