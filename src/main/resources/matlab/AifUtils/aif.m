cssmrange = 0:0.1:1

valueIdentity = aifSigmoid(cssmrange, 2.15, 0, 1.0)
delta = 0.2
valueDelta = aifSigmoid(cssmrange, delta, -100, 1.0)

plot(cssmrange, valueIdentity)
hold on
plot(cssmrange, valueDelta)


