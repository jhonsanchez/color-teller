## Objective

Practice blue/green deployment technique using kubernetes

## Exercise

### Blue/Green

- Clone repository
  ```bash
  git clone https://github.com/jhonsanchez/color-teller
  ```
- Build
  ```bash
  cd color-teller
  ./gradlew bootBuildImage
  ```
- Set namespace for exercise
    ```bash
    kubectl create namespace blue-green
    kubectl config set-context --current --namespace=blue-green
    ```
- Deploy blue
  ```bash
  kubectl apply -f k8s/deployment-blue.yaml
  kubectl apply -f k8s/service.yaml
  ```
- Check deployment and service
  ```bash
  kubectl get deployments
  ``` 
- Check URL
  ```bash
  http://localhost:8080
  ``` 

Update code

- Change the color in `src/main/resources/application.properties`
    ```properties
    color=blue
    ```
- Change version in `build.gradle`
    ```gradle
    version = '2.0.0'
    ```
- Build again
    ```bash
    ./gradlew bootBuildImage
    ```
- Deploy green
    ```bash
    kubectl apply -f k8s/deployment-green.yaml
    ```
- Update service yaml
    ```yaml
    apiVersion: v1
    kind: Service
    metadata:
      name: blue
    spec:
      selector:
        app: color-teller
        env: blue
    ports:
      - protocol: TCP
        port: 8080
        targetPort: 8080
    ```
- Deploy service
    ```bash
    kubectl apply -f k8s/service.yaml
    ```
- Check deployment and service
  ```bash
  kubectl get deployments
  ``` 
- Check URL
  ```bash
  http://localhost:8080
  ``` 