function [ retval ] = aifSigmoid( x, scale, inflection, tighten )
%aifSigmoid - the elementary function of the AIF functions, a sigmoid
%   created with the logistic function, and possibly shifted and scaled

  retval = scale * (-1 + 2 ./ (1 + exp(-(x - inflection) * tighten)))

end

