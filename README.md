# # java-petstore-tests

These are 8 scenarios for java-petstore-tests. Written using Maven, Java, Rest Assured, Serenity BDD. 

# Mandatory steps before run

Make sure you run the tests with correct env loaded. It is being propagated as test.env={local,stage,dev}.

# Run tests
Command to run the tests:

```
$ mvn -Dtest.env="local" clean verify
```

# Tests execution results on CI
You can see the tests are being passed on remote github host using CI workflow on Mac OS.
https://github.com/sergeyperin/java-petstore-tests/actions/runs/9448890325/job/26023882083


# Coverage
I don't control the state of AUT that is why something can be flaky and it is normal for the test task like this unless you mock everything.
Of course, coverage can be extended to test headers, authorizations, data correctness and some other values in the payload, etc.