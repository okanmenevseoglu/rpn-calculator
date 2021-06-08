docker build -t okanmenevseoglu.github.io/rpn-calculator:1.0.0 .

docker run -it --rm -p 8080:8080 --name rpn-calculator okanmenevseoglu.github.io/rpn-calculator:1.0.0
