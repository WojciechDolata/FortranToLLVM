      implicit none

      integer i, a, b
      b = 1


    10 if (b .le. 100) then
            b = 2*b
            goto 10
         endif

      do 20 i=0, 10
        b = b - 1
  20  continue

      b = 0

      end