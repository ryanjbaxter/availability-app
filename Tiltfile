SOURCE_IMAGE = os.getenv("SOURCE_IMAGE", default='gcr.io/cf-sandbox-rbaxter/tap-bootcamp/build-service/availability')
LOCAL_PATH = os.getenv("LOCAL_PATH", default='./')
NAMESPACE = os.getenv("NAMESPACE", default='dev-namespace')
OUTPUT_TO_NULL_COMMAND = os.getenv("OUTPUT_TO_NULL_COMMAND", default=' > /dev/null ')

k8s_custom_deploy(
    'availability',
    apply_cmd="tanzu apps workload apply -f ./config/workload.yaml --debug --live-update" +
        " --local-path " + LOCAL_PATH +
        " --source-image " + SOURCE_IMAGE +
        " --namespace " + NAMESPACE +
        " --yes " +
        OUTPUT_TO_NULL_COMMAND + 
        " && kubectl get workload availability --namespace " + NAMESPACE + " -o yaml",
    delete_cmd="tanzu apps workload delete -f ./config/workload.yaml --namespace " + NAMESPACE + " --yes" ,
    deps=['pom.xml', './target/classes'],
    container_selector='workload',
    live_update=[
        sync('./target/classes', '/workspace/BOOT-INF/classes')
    ]
)

k8s_resource('availability', port_forwards=["8080:8080", "8081:8081", "9005:9005"],
    extra_pod_selectors=[{'carto.run/workload-name': 'availability', 'app.kubernetes.io/component':'run'}])  
allow_k8s_contexts('arn:aws:eks:us-east-2:833443578445:cluster/rb-tap') 