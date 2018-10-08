function [U,G] = crawling(root,n)
% crawling is a function for the simpler version of spider modified from
% surfer.m
% [U,G] = crawling(root,n) starts at the URL root and follows
% links until it forms an adjacency matrixh with n nodes.
% U = a cell array of n strings, the URLs of the nodes.
% G = an n-by-n matrix with G(i,j)=1 if node j is linked to node i.
%
% This function shall be improved because the algorithm for finding 
% links is naive (currently just looking for the string 'http:').

% Initialize
U = cell(n,1);
hash = zeros(n,1);
G = zeros(n,n);
m = 1;
U{m} = root;
hash(m) = hashfun(root);

j = 1;
while j < n
   
   % Try to open a page.
   try
      page = urlread(U{j});
   catch
      j = j+1;
      continue
   end

   % Follow the links from the open page.
   for f = findstr('http:',page);

      % A link starts with 'http:' and ends with the next quote.
      e = min([findstr('"',page(f:end)) findstr('''',page(f:end))]);
      if isempty(e)
          continue
      end
      url = deblank(page(f:f+e-2));
      url(url<' ') = '!';   % Nonprintable characters
      if url(end) == '/', url(end) = []; end

      % Look for links that should be skipped.
      skips = {'.gif','.jpg','.jpeg','.pdf','.css','.asp','.mwc','.ram', ...
               '.cgi','lmscadsi','cybernet','w3.org','google','yahoo', ...
               'scripts','netscape','shockwave','webex','fansonly'};
      skip = any(url=='!') | any(url=='?');
      k = 0;
      while ~skip & (k < length(skips))
         k = k+1;
         skip = ~isempty(findstr(url,skips{k}));
      end
      if skip
         continue
      end

      % Check if page is already in url list.
      i = 0;
      for k = find(hash(1:m) == hashfun(url))';
         if isequal(U{k},url)
            i = k;
            break
         end
      end

      % Add a new url to the graph if there are fewer than n.
      if (i == 0) & (m < n)
         m = m+1;
         U{m} = url;
         hash(m) = hashfun(url);
         i = m;
      end

      % Add a new link.
      if i > 0
         G(i,j) = 1;
      end
   end
   j = j+1;
end


%------------------------
function h = hashfun(url)
% Almost unique numeric hash code for pages already visited.
h = length(url) + 1024*sum(url);