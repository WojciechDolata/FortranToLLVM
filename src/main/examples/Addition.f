      implicit none

      integer a, b
      a = 3
      b = 4

      if ( a .lt. b ) then
        a = b
        b = 4
      elseif (a .gt. b) then
        b = a
      else
        b = 5
      endif

      end